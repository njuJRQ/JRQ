package njurestaurant.njutakeout.springcontroller.user;


import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.user.EnterpriseBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class EnterpriseController {
    private  final EnterpriseBlService enterpriseBlService;

    @Autowired
    public EnterpriseController(EnterpriseBlService enterpriseBlService) {
        this.enterpriseBlService = enterpriseBlService;
    }

    private static String licensePath="";

    @ApiOperation(value = "获取营业执照", notes = "获取营业执照")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "license", value = "附件路径", required = true, dataType = "MultipartFile")
    })
    @RequestMapping(value = "/uploadLicense", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public void uploadLicense(@RequestParam("license")MultipartFile license){
        Map<String,Object> map= new HashMap<String,Object>();
        if(license.isEmpty()){
            map.put( "result", "error");
            map.put( "msg", "上传文件不能为空" );
        } else {

            // 获取文件名
            String fileName = license.getOriginalFilename();
            // 获取文件后缀

            // 用uuid作为文件名，防止生成的临时文件重复
            // MultipartFile to File
            //你的业务逻辑
            int bytesum = 0;
            int byteread = 0;
            InputStream inStream = null;    //读入原文件
            try {
                inStream = license.getInputStream();
                FileOutputStream fs = new FileOutputStream(fileName);
                byte[] buffer = new byte[200000000];
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;            //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            String[] temp=fileName.split("\\.");
            String thePath="";
            String path="";
            if(temp.length>2) {
                thePath = "record/license/" + uuid + "." + temp[3];
                path = "record/license/" + uuid + "." + temp[3];
            }else{
                thePath = "record/license/" + uuid + "." + temp[1];
                path = "record/license/" + uuid + "." + temp[1];
            }
            File tempfile=new File(path);
            if (tempfile.exists() && tempfile.isFile()) {
                tempfile.delete();
            }
            bytesum = 0;
            byteread = 0;
            try {
                inStream =new FileInputStream(fileName);
                FileOutputStream fs = new FileOutputStream(path);
                byte[] buffer = new byte[20000000];
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;            //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            licensePath=thePath;
        }
    }

    @ApiOperation(value = "用户升级自己为企业账户", notes = "用户升级自己为企业账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "enterpriseName", value = "企业名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "description", value = "企业描述,由前端限制描述不应少于30字", required = true, dataType = "String"),
            @ApiImplicitParam(name = "openid", value = "用户的微信openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "username", value = "企业用户管理员的username", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "企业用户管理员的密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "/setMyUserAsEnterprise", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> setMyUserAsEnterprise(@RequestParam(name="enterpriseName")String enterpriseName,@RequestParam(name="description")String description,@RequestParam(name="openid")String openid,@RequestParam(name="username")String username,@RequestParam(name="password")String password) {
        return new ResponseEntity<>(enterpriseBlService.setMyUserAsEnterprise(enterpriseName,description,licensePath,openid,username,password), HttpStatus.OK);
    }

    @ApiOperation(value = "根据ID获取特定企业信息", notes = "根据ID获取特定企业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "企业ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getEnterpriseById", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getEnterpriseById(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(enterpriseBlService.getEnterpriseById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "超级管理员获取所有企业信息", notes = "超级管理员获取所有企业信息")
    @RequestMapping(value = "/getAllEnterprises", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAllEnterprises() {
        return new ResponseEntity<>(enterpriseBlService.getAllEnterprises(), HttpStatus.OK);
    }

    @ApiOperation(value = "超级管理员将企业申请设置为通过审核", notes = "超级管理员将企业申请设置为通过审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "企业ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/verifyEnterpriseById", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> verifyEnterpriseById(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(enterpriseBlService.verifyEnterpriseById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "超级管理员删除企业", notes = "超级管理员删除企业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "企业ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteEnterpriseById", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteEnterpriseById(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(enterpriseBlService.deleteEnterpriseById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "用户获取自己已提交的企业用户信息", notes = "用户获取自己已提交的企业用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户的微信openid", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMySubmittedEnterprise", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMySubmittedEnterprise(@RequestParam(name="openid")String openid) throws NotExistException {
        return new ResponseEntity<>(enterpriseBlService.getMySubmittedEnterprise(openid), HttpStatus.OK);
    }

    @ApiOperation(value = "用户获取自己是否为企业用户", notes = "用户获取自己是否为企业用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户的微信openid", required = true, dataType = "String")
    })
    @RequestMapping(value = "/isMyUserEnterprise", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> isMyUserEnterprise(@RequestParam(name="openid")String openid) {
        return new ResponseEntity<>(enterpriseBlService.isMyUserEnterprise(openid), HttpStatus.OK);
    }

    @ApiOperation(value = "企业用户获取自己的管理员信息", notes = "企业用户获取自己的管理员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "企业用户的openid", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMyEnterpriseAdmin", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyEnterpriseAdmin(@RequestParam(name="openid")String openid) throws NotExistException {
        return new ResponseEntity<>(enterpriseBlService.getMyEnterpriseAdmin(openid), HttpStatus.OK);
    }

    @ApiOperation(value = "用户升级自己为企业账户", notes = "用户升级自己为企业账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "用户的微信openid", required = true, dataType = "String")
    })
    @RequestMapping(value = "/isAdminEnterprise", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> isAdminEnterprise(@RequestParam(name="adminId")String adminId) {
        return new ResponseEntity<>(enterpriseBlService.isAdminEnterprise(adminId), HttpStatus.OK);
    }

    @ApiOperation(value = "企业用户管理员获取自己发布的课程列表", notes = "企业用户管理员获取自己发布的课程列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMyPublishedCourseList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyPublishedCourseList(@RequestParam(name="adminId")String adminId) throws NotExistException {
        return new ResponseEntity<>(enterpriseBlService.getMyPublishedCourseList(adminId), HttpStatus.OK);
    }

    @ApiOperation(value = "企业用户管理员获取自己发布的文档列表", notes = "企业用户管理员获取自己发布的文档列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMyPublishedDocumentList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyPublishedDocumentList(@RequestParam(name="adminId")String adminId) throws NotExistException {
        return new ResponseEntity<>(enterpriseBlService.getMyPublishedDocumentList(adminId), HttpStatus.OK);
    }

    @ApiOperation(value = "企业用户管理员获取自己发布的项目列表", notes = "企业用户管理员获取自己发布的项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMyPublishedProjectList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyPublishedProjectList(@RequestParam(name="adminId")String adminId) throws NotExistException {
        return new ResponseEntity<>(enterpriseBlService.getMyPublishedProjectList(adminId), HttpStatus.OK);
    }


}

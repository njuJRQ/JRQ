package njurestaurant.njutakeout.springcontroller.article.project;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.article.project.ProjectBlService;
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
import java.util.*;

@RestController
public class ProjectController {
    private final ProjectBlService projectBlService;
    @Autowired
    public ProjectController(ProjectBlService projectBlService) {
        this.projectBlService = projectBlService;
    }


    @ApiOperation(value = "获取附件", notes = "获取附件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attachment", value = "附件路径", required = true, dataType = "MultipartFile")
    })
    @RequestMapping(value = "/uploadAttachment", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public String uploadAttachment(@RequestParam("attachment")MultipartFile attachment){
        Map<String,Object> map= new HashMap<String,Object>();
        if(attachment.isEmpty()){
            map.put( "result", "error");
            map.put( "msg", "上传文件不能为空" );
            return null;
        } else {

            // 获取文件名
            String fileName = attachment.getOriginalFilename();
            // 获取文件后缀

            // 用uuid作为文件名，防止生成的临时文件重复
            // MultipartFile to File
            //你的业务逻辑
            int bytesum = 0;
            int byteread = 0;
            InputStream inStream = null;    //读入原文件
            try {
                inStream = attachment.getInputStream();
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
            File file = new File(fileName);
            String[] temp=fileName.split("\\.");
            String thePath="record/project/"+uuid+"."+temp[1];
            String path="record/project/"+uuid+"."+temp[1];
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

            if (file.exists() && file.isFile()) {
                file.delete();
            }
            return thePath;
        }
    }
    @ApiOperation(value = "添加项目", notes = "添加项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "项目标题（简介）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "writerName", value = "作者名字", required = true, dataType = "String"),
            @ApiImplicitParam(name = "identity", value = "身份", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "联系方式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "city", value = "所在城市", required = true, dataType = "String"),
            @ApiImplicitParam(name = "industry", value = "所属行业", required = true, dataType = "String"),
            @ApiImplicitParam(name = "business", value = "业务标签", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "项目详情", required = true, dataType = "String"),
            @ApiImplicitParam(name = "money", value = "项目资金", required = true, dataType = "int"),
            @ApiImplicitParam(name = "date", value = "发布日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "attachment", value = "attachment", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addProject", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addProject(@RequestParam(name="title")String title, @RequestParam(name="writerName")String writerName, @RequestParam(name="identity")String identity, @RequestParam(name="phone")String phone, @RequestParam(name="city")String city, @RequestParam(name="industry")String industry, @RequestParam(name="business")String business, @RequestParam(name="content")String content, @RequestParam(name="money")int money, @RequestParam(name="date")String date,@RequestParam(name="attachment")String attachment) {
        ResponseEntity<Response> r = new ResponseEntity<>(projectBlService.addProject(title,writerName,identity,phone,city,industry,business,content,money,attachment), HttpStatus.OK);
        return r;
    }

    @ApiOperation(value = "根据项目ID获取项目", notes = "根据项目ID获取项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getProject", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getProject(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(projectBlService.getProject(id), HttpStatus.OK);
    }

    @ApiOperation(value = "获取项目列表", notes = "获取项目列表")
    @RequestMapping(value = "/getProjectList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getProjectList(){
        return new ResponseEntity<>(projectBlService.getProjectList(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据项目ID修改项目", notes = "根据项目ID修改项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "title", value = "项目标题（简介）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "writerName", value = "作者名字", required = true, dataType = "String"),
            @ApiImplicitParam(name = "identity", value = "身份", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "联系方式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "city", value = "所在城市", required = true, dataType = "String"),
            @ApiImplicitParam(name = "industry", value = "所属行业", required = true, dataType = "String"),
            @ApiImplicitParam(name = "business", value = "业务标签", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "项目详情", required = true, dataType = "String"),
            @ApiImplicitParam(name = "money", value = "项目资金", required = true, dataType = "int"),
            @ApiImplicitParam(name = "date", value = "发布日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "attachment", value = "attachment", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updateProject", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateProject(@RequestParam(name="id")String id,@RequestParam(name="title")String title, @RequestParam(name="writerName")String writerName, @RequestParam(name="identity")String identity, @RequestParam(name="phone")String phone, @RequestParam(name="city")String city, @RequestParam(name="industry")String industry, @RequestParam(name="business")String business, @RequestParam(name="content")String content, @RequestParam(name="money")int money, @RequestParam(name="date")String date,@RequestParam(name="attachment")String attachment) throws NotExistException {
        ResponseEntity<Response> r=new ResponseEntity<>(projectBlService.updateProject(id,title,writerName,identity,phone,city,industry,business,content,money,attachment), HttpStatus.OK);
        return r;
    }

    @ApiOperation(value = "根据项目ID删除项目", notes = "根据项目ID删除项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteProject", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteProject(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(projectBlService.deleteProject(id), HttpStatus.OK);
    }

    @ApiOperation(value = "用户查看特定项目", notes = "用户查看特定项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户的openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目ID", required = true, dataType = "String")

    })
    @RequestMapping(value = "/getMyProject", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyProject(@RequestParam(name="openid")String openid,@RequestParam(name="projectId")String projectId) throws NotExistException {
        return new ResponseEntity<>(projectBlService.getMyProject(openid,projectId), HttpStatus.OK);
    }

    @ApiOperation(value = "用户查看项目列表", notes = "用户查看项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户的openid", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMyProjectList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyProjectList(@RequestParam(name="openid")String openid) throws NotExistException {
        return new ResponseEntity<>(projectBlService.getMyProjectList(openid), HttpStatus.OK);
    }

    @ApiOperation(value = "获取某一篇项目文章时间戳前的10篇文章", notes = "获取某一篇项目文章时间戳前的10篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户的openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "项目文章ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMyProjectListBefore", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyProjectListBefore(@RequestParam(name="openid")String openid,@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(projectBlService.getMyProjectListBefore(openid,id), HttpStatus.OK);
    }
}

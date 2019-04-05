package njurestaurant.njutakeout.springcontroller.job;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.job.JobCardBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.parameters.job.JobCardParameters;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.job.JobCardListResponse;
import njurestaurant.njutakeout.response.job.JobCardResponse;
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
@RequestMapping("/jobCard")
public class JobCardController {
    private JobCardBlService jobCardBlService;
    @Autowired
    private JobCardController(JobCardBlService jobCardBlService){
        this.jobCardBlService=jobCardBlService;
    }

    @ApiOperation(value = "上传图片", notes = "上传图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "image", value = "image", required = true, dataType = "MultipartFile")
    })
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFeed(@RequestParam("image")MultipartFile image){
        Map<String,Object> map= new HashMap<String,Object>();
        if(image.isEmpty()){
            map.put( "result", "error");
            map.put( "msg", "上传文件不能为空" );
            return "上传文件不能为空";
        } else {

            // 获取文件名
            String fileName = image.getOriginalFilename();
            // 获取文件后缀

            // 用uuid作为文件名，防止生成的临时文件重复
            // MultipartFile to File
            //你的业务逻辑
            int bytesum = 0;
            int byteread = 0;
            InputStream inStream = null;    //读入原文件
            try {
                inStream = image.getInputStream();
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
            String thePath="record/user/job/"+uuid+"."+temp[1];
            String path="record/user/job/"+uuid+"."+temp[1];
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
    @ApiOperation(value = "新建求职信息", notes = "新建求职信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addJobCard(@RequestBody JobCardParameters parameters) throws NotExistException {
        return new ResponseEntity<>(jobCardBlService.add(parameters.getPhoto(),parameters.getExpectPosition(),parameters.getExpectWage(),parameters.getDegree(),parameters.getIntroduction(),parameters.isFresh(),parameters.getAge(),parameters.getWorkExperienceList(),parameters.getEducationExperienceList(),parameters.getOpenid()), HttpStatus.OK);
    }

    @ApiOperation(value = "修改招聘信息", notes = "修改招聘信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateJobCard(@RequestBody JobCardParameters parameters) throws NotExistException {
        return new ResponseEntity<>(jobCardBlService.update(parameters.getId(),parameters.getPhoto(),parameters.getExpectPosition(),parameters.getExpectWage(),parameters.getDegree(),parameters.getIntroduction(),parameters.isFresh(),parameters.getAge(),parameters.getWorkExperienceList(),parameters.getEducationExperienceList()), HttpStatus.OK);
    }

    @ApiOperation(value = "根据id获取jobCard内容", notes = "根据课id获取jobCard内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "job ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = JobCardResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findById(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(jobCardBlService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "根据id删除jobCard", notes = "根据id删除jobCard")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "job ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = JobCardResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteById(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(jobCardBlService.deleteById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "获取所有招聘信息", notes = "获取所有招聘信息")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response =JobCardListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAll(){
        return new ResponseEntity<>(jobCardBlService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据openid查找求职信息", notes = "根据openid查找求职信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户openid", required = true, dataType = "String")
    })
    @RequestMapping(value = "/findByUser", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = JobCardResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findByUser(@RequestParam(name="openid")String openid) throws NotExistException {
        return new ResponseEntity<>(jobCardBlService.findByUser(openid), HttpStatus.OK);
    }

    @ApiOperation(value = "根据职位查找求职信息", notes = "根据职位查找求职信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "expectPosition", value = "expectPosition", required = true, dataType = "String")
    })
    @RequestMapping(value = "/findByExpectPosition", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = JobCardResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findByExpectPosition(@RequestParam(name="expectPosition")String expectPosition) throws NotExistException {
        return new ResponseEntity<>(jobCardBlService.findByExpectPosition(expectPosition), HttpStatus.OK);
    }

    @ApiOperation(value = "根据定位查找求职信息", notes = "根据定位查找求职信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "city", value = "city", required = true, dataType = "String")
    })
    @RequestMapping(value = "/findByCity", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = JobCardResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findByCity(@RequestParam(name="city")String city) throws NotExistException {
        return new ResponseEntity<>(jobCardBlService.findByCity(city), HttpStatus.OK);
    }
}

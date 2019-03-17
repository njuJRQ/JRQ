package njurestaurant.njutakeout.springcontroller.job;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.job.JobCardBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.job.JobCardListResponse;
import njurestaurant.njutakeout.response.job.JobCardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobCard")
public class JobCardController {
    private JobCardBlService jobCardBlService;
    @Autowired
    private JobCardController(JobCardBlService jobCardBlService){
        this.jobCardBlService=jobCardBlService;
    }
    @ApiOperation(value = "新建求职信息", notes = "新建求职信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "expectPosition", value = "职位", required = true, dataType = "String"),
            @ApiImplicitParam(name = "expectWage", value = "薪水", required = true, dataType = "String"),
            @ApiImplicitParam(name = "experience", value = "经验", required = true, dataType = "String"),
            @ApiImplicitParam(name = "degree", value = "学历", required = true, dataType = "String"),
            @ApiImplicitParam(name = "introduction", value = "简介", required = true, dataType = "String"),
            @ApiImplicitParam(name="openid",value="用户openid",required = true,dataType = "String")

    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addJobCard(@RequestParam(name="expectPosition")String expectPosition,@RequestParam(name="expectWage")String expectWage,
                                              @RequestParam(name="experience")String experience,@RequestParam(name="degree")String degree,
                                              @RequestParam(name="introduction")String introduction, @RequestParam(name="openid")String openid) throws NotExistException {
        return new ResponseEntity<>(jobCardBlService.add(expectPosition,expectWage,experience,degree,introduction,openid), HttpStatus.OK);
    }

    @ApiOperation(value = "修改招聘信息", notes = "修改招聘信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "jobCard ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "expectPosition", value = "职位", required = true, dataType = "String"),
            @ApiImplicitParam(name = "expectWage", value = "薪水", required = true, dataType = "String"),
            @ApiImplicitParam(name = "experience", value = "经验", required = true, dataType = "String"),
            @ApiImplicitParam(name = "degree", value = "学历", required = true, dataType = "String"),
            @ApiImplicitParam(name = "introduction", value = "简介", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateJobCard(@RequestParam(name="id")String id,@RequestParam(name="expectPosition")String expectPosition,@RequestParam(name="expectWage")String expectWage,
                                                  @RequestParam(name="experience")String experience,@RequestParam(name="degree")String degree,
                                                  @RequestParam(name="introduction")String introduction) throws NotExistException {
        return new ResponseEntity<>(jobCardBlService.update(id,expectPosition,expectWage,experience,degree,introduction), HttpStatus.OK);
    }

    @ApiOperation(value = "根据id获取jobCard内容", notes = "根据课id获取jobCard内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "jobCard ID", required = true, dataType = "String")
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
}

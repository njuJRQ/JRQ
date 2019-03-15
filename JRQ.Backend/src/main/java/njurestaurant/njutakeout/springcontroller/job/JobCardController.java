package njurestaurant.njutakeout.springcontroller.job;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.job.JobCardBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
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
    @ApiOperation(value = "新建招聘信息", notes = "新建招聘信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "position", value = "职位", required = true, dataType = "String"),
            @ApiImplicitParam(name = "wage", value = "薪水", required = true, dataType = "String"),
            @ApiImplicitParam(name = "experienceRequirement", value = "经验要求", required = true, dataType = "String"),
            @ApiImplicitParam(name = "degreeRequirement", value = "学历要求", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "hr", value = "联系人", required = true, dataType = "String")
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addJobCard(@RequestParam(name="position")String position,@RequestParam(name="wage")String wage,
                                              @RequestParam(name="experienceRequirement")String experienceRequirement,@RequestParam(name="degreeRequirement")String degreeRequirement,
                                              @RequestParam(name="address")String address,@RequestParam(name="hr")String hr,
                                              @RequestParam(name="enterpriseId")String enterpriseId) throws NotExistException {
        return new ResponseEntity<>(jobCardBlService.add(position,wage,experienceRequirement,degreeRequirement,address,hr,enterpriseId), HttpStatus.OK);
    }

    @ApiOperation(value = "修改招聘信息", notes = "修改招聘信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "jobCard ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "position", value = "职位", required = true, dataType = "String"),
            @ApiImplicitParam(name = "wage", value = "薪水", required = true, dataType = "String"),
            @ApiImplicitParam(name = "experienceRequirement", value = "经验要求", required = true, dataType = "String"),
            @ApiImplicitParam(name = "degreeRequirement", value = "学历要求", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "hr", value = "联系人", required = true, dataType = "String")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateJobCard(@RequestParam(name="id")String id,@RequestParam(name="position")String position,@RequestParam(name="wage")String wage,
                                              @RequestParam(name="experienceRequirement")String experienceRequirement,@RequestParam(name="degreeRequirement")String degreeRequirement,
                                              @RequestParam(name="address")String address,@RequestParam(name="hr")String hr) throws NotExistException {
        return new ResponseEntity<>(jobCardBlService.update(id,position,wage,experienceRequirement,degreeRequirement,address,hr), HttpStatus.OK);
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

    @ApiOperation(value = "根据公司id查找招聘信息", notes = "根据公司id查找招聘信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "enterpriseJd", value = "公司ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/findByEnterprise", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = JobCardResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findByEnterprise(@RequestParam(name="enterpriseJd")String enterpriseId) throws NotExistException {
        return new ResponseEntity<>(jobCardBlService.findByEnterprise(enterpriseId), HttpStatus.OK);
    }
}

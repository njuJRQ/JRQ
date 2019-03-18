package njurestaurant.njutakeout.springcontroller.article.course;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.article.course.CourseGroupBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.parameters.course.CourseGroupParameters;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.article.course.CourseGroupListResponse;
import njurestaurant.njutakeout.response.article.course.CourseGroupResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courseGroup")
public class CourseGroupController {
    private final CourseGroupBlService courseGroupBlService;
    @Autowired
    public CourseGroupController(CourseGroupBlService courseGroupBlService){
        this.courseGroupBlService=courseGroupBlService;
    }

    @ApiOperation(value = "添加组合课程", notes = "添加组合课程")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addCourseGroup(@RequestBody CourseGroupParameters parameters) throws NotExistException {
        ResponseEntity<Response> r=new ResponseEntity<>(courseGroupBlService.add(parameters.getTitle(),parameters.getWriterName(),parameters.getCourses()), HttpStatus.OK);
        return r;
    }

    @ApiOperation(value = "修改组合课程", notes = "修改组合课程")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateCourseGroup(@RequestBody CourseGroupParameters parameters) throws NotExistException {
        ResponseEntity<Response> r=new ResponseEntity<>(courseGroupBlService.update(parameters.getId(),parameters.getTitle(),parameters.getWriterName(),parameters.getCourses()), HttpStatus.OK);
        return r;
    }

    @ApiOperation(value = "通过id查找课程组合", notes = "通过id查找课程组合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程组合id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CourseGroupResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findById(@RequestParam(name="id")String id) throws NotExistException {
        ResponseEntity<Response> r=new ResponseEntity<>(courseGroupBlService.findById(id), HttpStatus.OK);
        return r;
    }

    @ApiOperation(value = "获取所有课程组合", notes = "获取所有课程组合")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CourseGroupListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAllCourseGroup() {
        ResponseEntity<Response> r=new ResponseEntity<>(courseGroupBlService.getAll(), HttpStatus.OK);
        return r;
    }

    @ApiOperation(value = "获取用户已购买的课程组合", notes = "获取用户已购买的课程组合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "用户openId", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMyCourseGroup", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CourseGroupListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyCourseGroup(@RequestParam(name="openId")String openId) throws NotExistException {
        ResponseEntity<Response> r=new ResponseEntity<>(courseGroupBlService.getMyCourseGroupList(openId), HttpStatus.OK);
        return r;
    }
}

package njurestaurant.njutakeout.springcontroller.article.course;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.article.course.CourseBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {
    private final CourseBlService courseBlService;

    @Autowired
    public CourseController(CourseBlService courseBlService) {
        this.courseBlService = courseBlService;
    }

    @ApiOperation(value = "添加课程", notes = "添加课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "课程标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "image", value = "图片路径", required = true, dataType = "String"),
            @ApiImplicitParam(name = "writerName", value = "作者名字", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "发布日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "video", value = "视频路径", required = true, dataType = "String"),
            @ApiImplicitParam(name = "price", value = "课程价格", required = true, dataType = "int")
    })
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addCourse(@RequestParam(name="title")String title, @RequestParam(name="image")String image, @RequestParam(name="writerName")String writerName, @RequestParam(name="date")String date, @RequestParam(name="video")String video, @RequestParam(name="price")int price) {
        return new ResponseEntity<>(courseBlService.addCourse(title,image,writerName,date,0,video,price), HttpStatus.OK);
    }

    @ApiOperation(value = "根据课程ID获取课程内容", notes = "根据课程ID获取课程内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getCourse", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getCourse(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(courseBlService.getCourse(id), HttpStatus.OK);
    }

    @ApiOperation(value = "获取课程列表", notes = "获取课程列表")
    @RequestMapping(value = "/getCourseList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getCourseList(){
        return new ResponseEntity<>(courseBlService.getCourseList(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据课程ID修改课程内容", notes = "根据课程ID修改课程内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "title", value = "课程标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "image", value = "图片路径", required = true, dataType = "String"),
            @ApiImplicitParam(name = "writerName", value = "作者名字", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "发布日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "likeNum", value = "点赞数", required = true, dataType = "long"),
            @ApiImplicitParam(name = "video", value = "视频路径", required = true, dataType = "String"),
            @ApiImplicitParam(name = "price", value = "课程价格", required = true, dataType = "int")
    })
    @RequestMapping(value = "/updateCourse", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateCourse(@RequestParam(name="id")String id, @RequestParam(name="title")String title, @RequestParam(name="image")String image, @RequestParam(name="writerName")String writerName, @RequestParam(name="date")String date, @RequestParam(name="likeNum")long likeNum, @RequestParam(name="video")String video, @RequestParam(name="price")int price) throws NotExistException {
        return new ResponseEntity<>(courseBlService.updateCourse(id,title,image,writerName,date,likeNum,video,price), HttpStatus.OK);
    }


    @ApiOperation(value = "根据课程ID删除课程", notes = "根据课程ID删除课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteCourse", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteCourse(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(courseBlService.deleteCourse(id), HttpStatus.OK);
    }



}

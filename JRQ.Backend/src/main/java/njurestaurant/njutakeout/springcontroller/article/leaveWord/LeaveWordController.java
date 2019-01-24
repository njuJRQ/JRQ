package njurestaurant.njutakeout.springcontroller.article.leaveWord;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.article.leaveWord.LeaveWordBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaveWordController {
    private final LeaveWordBlService leaveWordBlService;

    @Autowired
    public LeaveWordController(LeaveWordBlService leaveWordBlService) {
        this.leaveWordBlService = leaveWordBlService;
    }
    @ApiOperation(value = "用户给某个课程发布自己的留言", notes = "用户给某个课程发布自己的留言")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "courseId", value = "课程id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "writerOpenid", value = "作者id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addLeaveWord", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addLeaveWord(
            @RequestParam(name="content")String content,
            @RequestParam(name="courseId") String courseId,
            @RequestParam(name="writerOpenid")String writerOpenid) {
        ResponseEntity<Response> r=
                new ResponseEntity<>(leaveWordBlService.publishMyLeaveWord(content,courseId,writerOpenid), HttpStatus.OK);
        return r;
    }

    @ApiOperation(value = "用户根据留言ID删除自己的留言", notes = "用户根据留言ID删除自己的留言")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "留言id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteMyLeaveWord", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteMyLeaveWord(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(leaveWordBlService.deleteMyLeaveWord(id), HttpStatus.OK);
    }


}

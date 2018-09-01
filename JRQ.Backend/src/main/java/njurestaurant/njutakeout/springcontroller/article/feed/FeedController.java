package njurestaurant.njutakeout.springcontroller.article.feed;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.article.feed.FeedBlService;
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
public class FeedController {
    private final FeedBlService feedBlService;
    @Autowired
    public FeedController(FeedBlService feedBlService) {
        this.feedBlService = feedBlService;
    }

    @ApiOperation(value = "用户发布自己的圈子文章", notes = "用户发布自己的圈子文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "images", value = "图片路径", required = true, dataType = "List<String>"),
            @ApiImplicitParam(name = "writerOpenid", value = "作者id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "发布日期", required = true, dataType = "String")
    })
    @RequestMapping(value = "/publishMyFeed", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> publishMyFeed(@RequestParam(name="content")String content, @RequestParam(name="images")List<String> images, @RequestParam(name="writerOpenid")String writerOpenid, @RequestParam(name="date")String date) {
        return new ResponseEntity<>(feedBlService.publishMyFeed(content,images,writerOpenid,date), HttpStatus.OK);
    }

    @ApiOperation(value = "根据圈子文章ID获取全文", notes = "根据圈子文章ID获取全文")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "圈子文章ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getFeed", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getFeed(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(feedBlService.getFeed(id), HttpStatus.OK);
    }

    @ApiOperation(value = "获取圈子全部文章信息", notes = "获取圈子全部文章信息")
    @RequestMapping(value = "/getFeedList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getFeedList(){
        return new ResponseEntity<>(feedBlService.getFeedList(), HttpStatus.OK);
    }

    @ApiOperation(value = "用户获取自己发布过的文章摘要", notes = "用户获取自己发布过的文章摘要")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户的微信openid", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMyHistoryAbstractList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyHistoryAbstractList(@RequestParam(name="openid")String openid) throws NotExistException {
        return new ResponseEntity<>(feedBlService.getMyHistoryAbstractList(openid), HttpStatus.OK);
    }

    @ApiOperation(value = "用户更新自己发布的圈子文章信息", notes = "用户更新自己发布的圈子文章信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "圈子文章ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "images", value = "图片路径", required = true, dataType = "List<String>"),
            @ApiImplicitParam(name = "date", value = "发布日期", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updateMyFeed", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateMyFeed(@RequestParam(name="id")String id,@RequestParam(name="content")String content, @RequestParam(name="images")List<String> images, @RequestParam(name="date")String date) throws NotExistException {
        return new ResponseEntity<>(feedBlService.updateMyFeed(id,content,images,date), HttpStatus.OK);
    }

    @ApiOperation(value = "用户根据圈子文章ID删除自己的圈子文章", notes = "用户根据圈子文章ID删除自己的圈子文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "圈子文章ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteMyFeed", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteMyFeed(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(feedBlService.deleteMyFeed(id), HttpStatus.OK);
    }

}

package njurestaurant.njutakeout.springcontroller.article.news;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.article.news.NewsBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsController {
    private final NewsBlService newsBlService;

    @Autowired
    public NewsController(NewsBlService newsBlService) {
        this.newsBlService = newsBlService;
    }

    @ApiOperation(value = "获取摘要列表：包括首页和圈子", notes = "获取摘要列表：包括首页和圈子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "请求方名称，可取值\"user\",\"admin\"", required = true, dataType = "String"),
            @ApiImplicitParam(name = "newsId", value = "参照新闻的id（News表中的ID，而非特定新闻表的ID）。ID为\"\"则返回当前最新的新闻", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getNewsListBefore", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getNewsListBefore(@RequestParam(name="type")String type, @RequestParam(name="newsId")String newsId) throws NotExistException {
        return new ResponseEntity<>(newsBlService.getNewsListBefore(type,newsId), HttpStatus.OK);
    }

    @ApiOperation(value = "获取摘要列表：包括首页和圈子", notes = "获取摘要列表：包括首页和圈子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "新闻的全局ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "新的新闻时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "新的新闻内容", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updateNews", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateNews(@RequestParam(name="newsId")String newsId, @RequestParam(name="time")String time, @RequestParam(name="content")String content) throws NotExistException {
        return new ResponseEntity<>(newsBlService.updateNews(newsId,time,content), HttpStatus.OK);
    }

    @ApiOperation(value = "删除某条新闻", notes = "删除某条新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "参照新闻的id（News表中的ID，而非特定新闻表的ID）。ID为\"\"则返回当前最新的新闻", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteNews", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteNews(@RequestParam(name="newsId")String newsId) {
        return new ResponseEntity<>(newsBlService.deleteNews(newsId), HttpStatus.OK);
    }

    @ApiOperation(value = "根据全局新闻ID获取新闻", notes = "根据全局新闻ID获取新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "参照新闻的id（News表中的ID，而非特定新闻表的ID）。ID为\"\"则返回当前最新的新闻", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getNews", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getNews(@RequestParam(name="newsId")String newsId) {
        return new ResponseEntity<>(newsBlService.getNews(newsId), HttpStatus.OK);
    }

    @ApiOperation(value = "获取当前数据库中总新闻条数", notes = "获取当前数据库中总新闻条数")
    @RequestMapping(value = "/getNewsNumber", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getNewsNumber() {
        return new ResponseEntity<>(newsBlService.getNewsNumber(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据条件搜索资讯列表", notes = "根据条件搜索资讯列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "搜索条件", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getNewsListByCondition", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getNewsListByCondition(@RequestParam(name="condition")String condition)  {
        return new ResponseEntity<>(newsBlService.getNewsListByCondition(condition), HttpStatus.OK);
    }
}

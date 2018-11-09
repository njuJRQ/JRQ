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
}

package njurestaurant.njutakeout.springcontroller.article;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.article.ArticleBlService;
import njurestaurant.njutakeout.exception.AlreadyExistException;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {
    private final ArticleBlService articleBlService;

    @Autowired
    public ArticleController(ArticleBlService articleBlService) {
        this.articleBlService = articleBlService;
    }

    @ApiOperation(value = "获取摘要列表：包括首页和圈子", notes = "获取摘要列表：包括首页和圈子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kind", value = "文章类型，可能值：course，document，project，feed分别对应课程，文档，项目，圈子", required = true, dataType = "String"),
            @ApiImplicitParam(name = "openid", value = "只有在文章类型为feed时才会用到", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getAbstractList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAbstractList(@RequestParam(name="kind")String kind,@RequestParam(name="openid")String openid) throws NotExistException {
        return new ResponseEntity<>(articleBlService.getAbstractList(kind,openid), HttpStatus.OK);
    }

    @ApiOperation(value = "获取文章详情", notes = "获取文章详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kind", value = "文章类型，可能值：course，document，project，feed分别对应课程，文档，项目，圈子", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getArticle", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getArticle(@RequestParam(name="kind")String kind,@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(articleBlService.getArticle(kind,id), HttpStatus.OK);
    }

    @ApiOperation(value = "点赞，若已经赞过则取消赞", notes = "点赞，若已经赞过则取消赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户微信ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "kind", value = "文章类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "articleId", value = "文章ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/likePlus", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> likePlus(@RequestParam(name="openid")String openid,@RequestParam(name="kind")String kind,@RequestParam(name="articleId")String articleId) throws NotExistException, AlreadyExistException {
        return new ResponseEntity<>(articleBlService.likePlus(openid,kind,articleId), HttpStatus.OK);
    }

    @ApiOperation(value = "根据条件搜索文章摘要列表", notes = "根据条件搜索文章摘要列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "搜索条件", required = true, dataType = "String"),
            @ApiImplicitParam(name = "condition", value = "搜索条件", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getAbstractListByCondition", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAbstractListByCondition(@RequestParam(name="openid")String openid,@RequestParam(name="condition")String condition)  {
        return new ResponseEntity<>(articleBlService.getAbstractListByCondition(openid,condition), HttpStatus.OK);
    }

    @ApiOperation(value = "根据点赞数排序获取文章摘要列表", notes = "根据点赞数排序获取文章摘要列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "搜索条件", required = true, dataType = "String"),
            @ApiImplicitParam(name = "kind", value = "文章类型", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getAbstractListByLikeNum", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAbstractListByLikeNum(@RequestParam(name="openid")String openid,@RequestParam(name="kind")String kind)  {
        return new ResponseEntity<>(articleBlService.getAbstractListByLikeNum(openid,kind), HttpStatus.OK);
    }

    @ApiOperation(value = "获取摘要列表：仅包括首页不包括圈子，一次加载10条(User)", notes = "获取摘要列表：仅包括首页不包括圈子，一次加载10条(User)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kind", value = "文章类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "openid", value = "用户openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "articleId", value = "文章ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "articleType", value = "参数中文章ID的文章类型", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getAbstractListBefore", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAbstractListBefore(@RequestParam(name="kind")String kind,@RequestParam(name="openid")String openid,@RequestParam(name="articleId")String articleId,@RequestParam(name="articleType")String articleType) throws NotExistException {
        return new ResponseEntity<>(articleBlService.getAbstractListBefore(kind,openid,articleId,articleType), HttpStatus.OK);
    }
}

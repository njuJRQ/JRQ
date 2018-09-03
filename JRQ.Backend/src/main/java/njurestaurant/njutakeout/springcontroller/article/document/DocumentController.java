package njurestaurant.njutakeout.springcontroller.article.document;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.article.document.DocumentBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DocumentController {
    private final DocumentBlService documentBlService;
    @Autowired
    public DocumentController(DocumentBlService documentBlService) {
        this.documentBlService = documentBlService;
    }

    @ApiOperation(value = "添加文档", notes = "添加文档")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文档标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "文档内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "writerName", value = "作者名字", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "发布日期", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addDocument", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addDocument(@RequestParam(name="title")String title, @RequestParam(name="content")String content, @RequestParam(name="writerName")String writerName, @RequestParam(name="date")String date) {
        return new ResponseEntity<>(documentBlService.addDocument(title,content,writerName,date,0), HttpStatus.OK);
    }

    @ApiOperation(value = "根据文档ID获取文档", notes = "根据文档ID获取文档")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文档ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getDocument", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getDocument(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(documentBlService.getDocument(id), HttpStatus.OK);
    }

    @ApiOperation(value = "获取文档列表", notes = "获取文档列表")
    @RequestMapping(value = "/getDocumentList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getDocumentList(){
        return new ResponseEntity<>(documentBlService.getDocumentList(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据文档ID修改文档", notes = "根据文档ID修改文档")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "title", value = "课程标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "图片路径", required = true, dataType = "String"),
            @ApiImplicitParam(name = "writerName", value = "作者名字", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "发布日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "likeNum", value = "点赞数", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updateDocument", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateDocument(@RequestParam(name="id")String id,@RequestParam(name="title")String title, @RequestParam(name="content")String content, @RequestParam(name="writerName")String writerName, @RequestParam(name="date")String date, @RequestParam(name="likeNum")String likeNum) throws NotExistException {
        return new ResponseEntity<>(documentBlService.updateDocument(id,title,content,writerName,date,Long.parseLong(likeNum)), HttpStatus.OK);
    }

    @ApiOperation(value = "根据文档ID删除文档", notes = "根据文档ID删除文档")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文档ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteDocument", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteDocument(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(documentBlService.deleteDocument(id), HttpStatus.OK);
    }
}

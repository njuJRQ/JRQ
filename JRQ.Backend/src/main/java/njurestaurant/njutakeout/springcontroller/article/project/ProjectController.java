package njurestaurant.njutakeout.springcontroller.article.project;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.article.project.ProjectBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {
    private final ProjectBlService projectBlService;
    @Autowired
    public ProjectController(ProjectBlService projectBlService) {
        this.projectBlService = projectBlService;
    }

    @ApiOperation(value = "添加项目", notes = "添加项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "项目标题（简介）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "writerName", value = "作者名字", required = true, dataType = "String"),
            @ApiImplicitParam(name = "identity", value = "身份", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "联系方式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "city", value = "所在城市", required = true, dataType = "String"),
            @ApiImplicitParam(name = "industry", value = "所属行业", required = true, dataType = "String"),
            @ApiImplicitParam(name = "business", value = "业务标签", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "项目详情", required = true, dataType = "String"),
            @ApiImplicitParam(name = "money", value = "项目资金", required = true, dataType = "int"),
            @ApiImplicitParam(name = "attachment", value = "附件路径", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "发布日期", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addProject", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addProject(@RequestParam(name="title")String title, @RequestParam(name="writerName")String writerName, @RequestParam(name="identity")String identity, @RequestParam(name="phone")String phone, @RequestParam(name="city")String city, @RequestParam(name="industry")String industry, @RequestParam(name="business")String business, @RequestParam(name="content")String content, @RequestParam(name="money")int money, @RequestParam(name="attachment")String attachment,@RequestParam(name="date")String date) {
        return new ResponseEntity<>(projectBlService.addProject(title,writerName,identity,phone,city,industry,business,content,money,attachment,date), HttpStatus.OK);
    }

    @ApiOperation(value = "根据项目ID获取项目", notes = "根据项目ID获取项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getProject", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getProject(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(projectBlService.getProject(id), HttpStatus.OK);
    }

    @ApiOperation(value = "获取项目列表", notes = "获取项目列表")
    @RequestMapping(value = "/getProjectList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getProjectList(){
        return new ResponseEntity<>(projectBlService.getProjectList(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据项目ID修改项目", notes = "根据项目ID修改项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "title", value = "项目标题（简介）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "writerName", value = "作者名字", required = true, dataType = "String"),
            @ApiImplicitParam(name = "identity", value = "身份", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "联系方式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "city", value = "所在城市", required = true, dataType = "String"),
            @ApiImplicitParam(name = "industry", value = "所属行业", required = true, dataType = "String"),
            @ApiImplicitParam(name = "business", value = "业务标签", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "项目详情", required = true, dataType = "String"),
            @ApiImplicitParam(name = "money", value = "项目资金", required = true, dataType = "int"),
            @ApiImplicitParam(name = "attachment", value = "附件路径", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "发布日期", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updateProject", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateProject(@RequestParam(name="id")String id,@RequestParam(name="title")String title, @RequestParam(name="writerName")String writerName, @RequestParam(name="identity")String identity, @RequestParam(name="phone")String phone, @RequestParam(name="city")String city, @RequestParam(name="industry")String industry, @RequestParam(name="business")String business, @RequestParam(name="content")String content, @RequestParam(name="money")int money, @RequestParam(name="attachment")String attachment,@RequestParam(name="date")String date) throws NotExistException {
        return new ResponseEntity<>(projectBlService.updateProject(id,title,writerName,identity,phone,city,industry,business,content,money,attachment,date), HttpStatus.OK);
    }

    @ApiOperation(value = "根据项目ID删除项目", notes = "根据项目ID删除项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteProject", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteProject(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(projectBlService.deleteProject(id), HttpStatus.OK);
    }
}

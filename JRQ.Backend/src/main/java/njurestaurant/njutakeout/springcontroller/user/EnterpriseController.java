package njurestaurant.njutakeout.springcontroller.user;


import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.user.EnterpriseBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EnterpriseController {
    private  final EnterpriseBlService enterpriseBlService;

    @Autowired
    public EnterpriseController(EnterpriseBlService enterpriseBlService) {
        this.enterpriseBlService = enterpriseBlService;
    }

    @ApiOperation(value = "用户升级自己为企业账户", notes = "用户升级自己为企业账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户的微信openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "username", value = "企业用户管理员的username", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "企业用户管理员的密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "/setMyUserAsEnterprise", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> setMyUserAsEnterprise(@RequestParam(name="openid")String openid,@RequestParam(name="username")String username,@RequestParam(name="password")String password) {
        return new ResponseEntity<>(enterpriseBlService.setMyUserAsEnterprise(openid,username,password), HttpStatus.OK);
    }

    @ApiOperation(value = "用户升级自己为企业账户", notes = "用户升级自己为企业账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "用户的微信openid", required = true, dataType = "String")
    })
    @RequestMapping(value = "/isAdminEnterprise", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> isAdminEnterprise(@RequestParam(name="adminId")String adminId) {
        return new ResponseEntity<>(enterpriseBlService.isAdminEnterprise(adminId), HttpStatus.OK);
    }

    @ApiOperation(value = "企业用户管理员获取自己发布的课程列表", notes = "企业用户管理员获取自己发布的课程列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMyPublishedCourseList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyPublishedCourseList(@RequestParam(name="adminId")String adminId) throws NotExistException {
        return new ResponseEntity<>(enterpriseBlService.getMyPublishedCourseList(adminId), HttpStatus.OK);
    }

    @ApiOperation(value = "企业用户管理员获取自己发布的文档列表", notes = "企业用户管理员获取自己发布的文档列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMyPublishedDocumentList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyPublishedDocumentList(@RequestParam(name="adminId")String adminId) throws NotExistException {
        return new ResponseEntity<>(enterpriseBlService.getMyPublishedDocumentList(adminId), HttpStatus.OK);
    }

    @ApiOperation(value = "企业用户管理员获取自己发布的项目列表", notes = "企业用户管理员获取自己发布的项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMyPublishedProjectList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyPublishedProjectList(@RequestParam(name="adminId")String adminId) throws NotExistException {
        return new ResponseEntity<>(enterpriseBlService.getMyPublishedProjectList(adminId), HttpStatus.OK);
    }


}

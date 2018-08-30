package njurestaurant.njutakeout.springcontroller.admin;


import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.admin.AdminBlService;
import njurestaurant.njutakeout.blservice.event.EventBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.publicdatas.account.Role;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.admin.AdminListResponse;
import njurestaurant.njutakeout.response.admin.AdminResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class adminController {
    private final AdminBlService adminBlService;

    @Autowired
    public adminController(AdminBlService adminBlService) {
        this.adminBlService = adminBlService;
    }

    @ApiOperation(value = "添加管理员", notes = "添加管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limits", value = "权限", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "日期", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addadmin", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public InfoResponse addAdmin(@RequestParam(name="username")String username, @RequestParam(name="password")String password, @RequestParam(name="limits")String limits, @RequestParam(name="date")String date){
        InfoResponse info=adminBlService.addAdmin(username, password, limits, date);
        return info;
    }

    @ApiOperation(value = "获取管理员列表", notes = "获取管理员列表")
    @RequestMapping(value = "/getAdminList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public AdminListResponse getAdminList(){
        AdminListResponse r=adminBlService.getAdminList();
        return r;
    }

    @ApiOperation(value = "获取管理员", notes = "获取管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员编号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getAdmin", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public AdminResponse getAdmin(@RequestParam(name="id")String id) throws NotExistException {
        AdminResponse r=adminBlService.getAdmin(id);
        return r;
    }

    @ApiOperation(value = "修改管理员", notes = "修改管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limits", value = "权限", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "日期", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updateAdmin", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public InfoResponse updateAdmin(@RequestParam(name="id")String id,@RequestParam(name="username")String username, @RequestParam(name="password")String password, @RequestParam(name="limits")String limits, @RequestParam(name="date")String date) throws NotExistException {
        InfoResponse info=adminBlService.updateAdmin(id,username, password, limits, date);
        return info;
    }

    @ApiOperation(value = "删除管理员", notes = "删除管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员编号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteAdmin", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public InfoResponse deleteAdmin(@RequestParam(name="id")String id) {
        InfoResponse r=adminBlService.deleteAdmin(id);
        return r;
    }




}


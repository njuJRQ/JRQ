package njurestaurant.njutakeout.springcontroller.user;


import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.user.PrivilegeBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PrivilegeController
{
    private  final PrivilegeBlService privilegeBlService;
    @Autowired
    public PrivilegeController(PrivilegeBlService privilegeBlService) {
        this.privilegeBlService = privilegeBlService;
    }

    @ApiOperation(value = "添加特权", notes = "添加特权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "特权名称，取值：enterprise", required = true, dataType = "String"),
            @ApiImplicitParam(name = "price", value = "特权价格", required = true, dataType = "int")
    })
    @RequestMapping(value = "/addPrivilege", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addPrivilege(@RequestParam(name="name")String name,@RequestParam(name="price")int price){
        return new ResponseEntity<>(privilegeBlService.addPrivilege(name,price), HttpStatus.OK);
    }

    @ApiOperation(value = "根据特权名称获取特权信息", notes = "根据特权名称获取特权信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "特权名称，取值：enterprise", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getPrivilege", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getPrivilege(@RequestParam(name="name")String name) throws NotExistException {
        return new ResponseEntity<>(privilegeBlService.getPrivilege(name), HttpStatus.OK);
    }

    @ApiOperation(value = "获取所有特权列表", notes = "获取所有特权列表")
    @RequestMapping(value = "/getPrivilegeList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getPrivilegeList()  {
        return new ResponseEntity<>(privilegeBlService.getPrivilegeList(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据特权名称更新特权价格", notes = "根据特权名称更新特权价格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "特权名称，取值：enterprise", required = true, dataType = "String"),
            @ApiImplicitParam(name = "price", value = "特权价格", required = true, dataType = "int")
    })
    @RequestMapping(value = "/updatePrivilege", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updatePrivilege(@RequestParam(name="name")String name,@RequestParam(name="price")int price){
        return new ResponseEntity<>(privilegeBlService.updatePrivilege(name,price), HttpStatus.OK);
    }

    @ApiOperation(value = "根据名称删除特权", notes = "根据名称删除特权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "特权名称，取值：enterprise", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deletePrivilege", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteUser(@RequestParam(name="name")String name) throws NotExistException {
        return new ResponseEntity<>(privilegeBlService.deletePrivilege(name), HttpStatus.OK);
    }
}

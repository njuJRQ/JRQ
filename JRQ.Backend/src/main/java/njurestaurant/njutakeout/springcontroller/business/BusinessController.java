package njurestaurant.njutakeout.springcontroller.business;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.business.BusinessBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.business.BusinessListResponse;
import njurestaurant.njutakeout.response.business.BusinessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business")
public class BusinessController {
    private final BusinessBlService businessBlService;
    @Autowired
    public BusinessController(BusinessBlService businessBlService){
        this.businessBlService=businessBlService;
    }

    @ApiOperation(value = "新建业务", notes = "新建业务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "linkMan", value = "联系人", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "电话", required = true, dataType = "String"),
            @ApiImplicitParam(name = "agencyName", value = "机构名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectInfo", value = "项目信息", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectRef", value = "项目关联", required = true, dataType = "String"),
            @ApiImplicitParam(name = "marketType", value = "市场类型", required = true, dataType = "String"),
            @ApiImplicitParam(name="writerOpenid",value="用户openid",required = true,dataType = "String")

    })
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> add(@RequestParam(name="linkMan")String linkMan, @RequestParam(name="phone")String phone,
                                               @RequestParam(name="agencyName")String agencyName, @RequestParam(name="projectInfo")String projectinfo,
                                               @RequestParam(name="projectRef")String projectRef, @RequestParam(name="marketType")String marketType,
                                               @RequestParam(name="writerOpenid")String writerOpenid) {
        return new ResponseEntity<>(businessBlService.add(linkMan,phone,agencyName,projectinfo,projectRef,marketType,writerOpenid), HttpStatus.OK);
    }

    @ApiOperation(value = "修改业务", notes = "修改业务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "linkMan", value = "联系人", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "电话", required = true, dataType = "String"),
            @ApiImplicitParam(name = "agencyName", value = "机构名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectInfo", value = "项目信息", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectRef", value = "项目关联", required = true, dataType = "String"),
            @ApiImplicitParam(name = "marketType", value = "市场类型", required = true, dataType = "String"),
            @ApiImplicitParam(name="id",value="id",required = true,dataType = "String")

    })
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> update(@RequestParam(name="linkMan")String linkMan, @RequestParam(name="phone")String phone,
                                               @RequestParam(name="agencyName")String agencyName, @RequestParam(name="projectInfo")String projectInfo,
                                               @RequestParam(name="projectRef")String projectRef, @RequestParam(name="marketType")String marketType,
                                               @RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(businessBlService.update(id,linkMan,phone,agencyName,projectInfo,projectRef,marketType), HttpStatus.OK);
    }

    @ApiOperation(value = "根据id删除业务内容", notes = "根据课id删除业务内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "business ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteById(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(businessBlService.deleteById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "根据id获取业务内容", notes = "根据课id获取业务内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "business ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BusinessResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findById(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(businessBlService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "根据市场类型获取业务内容", notes = "根据市场类型获取业务内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "marketType", value = "市场类型", required = true, dataType = "String")
    })
    @RequestMapping(value = "/findByMarketType", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BusinessListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findByMarketType(@RequestParam(name="marketType")String marketType){
        return new ResponseEntity<>(businessBlService.findByMarketType(marketType), HttpStatus.OK);
    }

    @ApiOperation(value = "根据项目关联获取业务内容", notes = "根据项目关联获取业务内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectRef", value = "项目关联", required = true, dataType = "String")
    })
    @RequestMapping(value = "/findByProjectRef", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BusinessListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findByProjectRef(@RequestParam(name="projectRef")String projectRef) {
        return new ResponseEntity<>(businessBlService.findByProjectRef(projectRef), HttpStatus.OK);
    }

    @ApiOperation(value = "获取全部业务", notes = "获取全部业务")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BusinessListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAll() {
        return new ResponseEntity<>(businessBlService.getAll(), HttpStatus.OK);
    }
}

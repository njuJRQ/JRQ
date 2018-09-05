package njurestaurant.njutakeout.springcontroller.purchase;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.purchase.PurchaseBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.BoolResponse;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class purchaseController {
    private final PurchaseBlService purchaseBlService;
    @Autowired
    public purchaseController(PurchaseBlService purchaseBlService) {
        this.purchaseBlService = purchaseBlService;
    }

    @ApiOperation(value = "用户下订单", notes = "用户下订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户自己的微信openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "要查看的用户的openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "detail", value = "用户自己的微信openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "price", value = "要查看的用户的openid", required = true, dataType = "int"),
            @ApiImplicitParam(name = "date", value = "用户自己的微信openid", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addPurchase", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public BoolResponse addPurchase(@RequestParam(name="openid")String openid, @RequestParam(name="type")String type, @RequestParam(name="detail")String detail, @RequestParam(name="price")int price, @RequestParam(name="date")String date) {
        return purchaseBlService.addPurchase(openid,type,detail,price,date);
    }

    @ApiOperation(value = "根据订单号获取单个订单信息", notes = "根据订单号获取单个订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单ID", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/getPurchase", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getPurchase(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(purchaseBlService.getPurchase(id), HttpStatus.OK);
    }

    @ApiOperation(value = "用户获取自己的订单列表", notes = "用户获取自己的订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户微信openid", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/getMyPurchaseList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyPurchaseList(@RequestParam(name="openid")String openid) {
        return new ResponseEntity<>(purchaseBlService.getMyPurchaseList(openid), HttpStatus.OK);
    }

    @ApiOperation(value = "获取所有订单信息", notes = "获取所有订单信息")
    @RequestMapping(value = "/getPurchaseList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getPurchaseList() {
        return new ResponseEntity<>(purchaseBlService.getPurchaseList(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据订单ID删除订单信息", notes = "根据订单ID删除订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户微信openid", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/deletePurchase", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deletePurchase(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(purchaseBlService.deletePurchase(id), HttpStatus.OK);
    }






}

package njurestaurant.njutakeout.springcontroller.article.ad;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.article.ad.AdBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdController {
    private final AdBlService adBlService;

    @Autowired
    public AdController(AdBlService adBlService) {
        this.adBlService = adBlService;
    }

    @ApiOperation(value = "添加广告", notes = "添加广告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "image", value = "广告图片URL", required = true, dataType = "String"),
            @ApiImplicitParam(name = "link", value = "广告导向的链接", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addAd", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addAd(@RequestParam(name="image")String image,@RequestParam(name="link")String link) {
        return new ResponseEntity<>(adBlService.addAd(image,link), HttpStatus.OK);
    }

    @ApiOperation(value = "根据广告ID获取广告", notes = "根据广告ID获取广告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getAd", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAd(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(adBlService.getAd(id), HttpStatus.OK);
    }

    @ApiOperation(value = "获取被选中在首页展示的广告", notes = "获取被选中在首页展示的广告")
    @RequestMapping(value = "/getCheckedAd", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getCheckedAd() {
        return new ResponseEntity<>(adBlService.getCheckedAd(), HttpStatus.OK);
    }

    @ApiOperation(value = "获取所有广告信息", notes = "获取所有广告信息")
    @RequestMapping(value = "/getAdList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAdList() {
        return new ResponseEntity<>(adBlService.getAdList(), HttpStatus.OK);
    }

    @ApiOperation(value = "设置在首页展示的广告", notes = "设置在首页展示的广告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "被选中的广告ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/setCheckedAd", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> setCheckedAd(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(adBlService.setCheckedAd(id), HttpStatus.OK);
    }

    @ApiOperation(value = "根据广告ID修改广告信息", notes = "根据广告ID修改广告信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "image", value = "广告图片URL", required = true, dataType = "String"),
            @ApiImplicitParam(name = "link", value = "广告导向的链接", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updateAd", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateAd(@RequestParam(name="id")String id,@RequestParam(name="image")String image,@RequestParam(name="link")String link) throws NotExistException {
        return new ResponseEntity<>(adBlService.updateAd(id,image,link), HttpStatus.OK);
    }


    @ApiOperation(value = "根据广告ID删除广告", notes = "根据广告ID删除广告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteAd", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteAd(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(adBlService.deleteAd(id), HttpStatus.OK);
    }








}

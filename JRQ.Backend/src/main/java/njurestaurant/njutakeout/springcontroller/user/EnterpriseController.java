package njurestaurant.njutakeout.springcontroller.user;


import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.user.EnterpriseBlService;
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
            @ApiImplicitParam(name = "openid", value = "用户的微信openid", required = true, dataType = "String")
    })
    @RequestMapping(value = "/setMyUserAsEnterprise", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> setMyUserAsEnterprise(@RequestParam(name="openid")String openid) {
        return new ResponseEntity<>(enterpriseBlService.setMyUserAsEnterprise(openid), HttpStatus.OK);
    }
}

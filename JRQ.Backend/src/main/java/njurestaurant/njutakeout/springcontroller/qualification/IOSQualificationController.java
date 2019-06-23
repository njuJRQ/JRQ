package njurestaurant.njutakeout.springcontroller.qualification;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import njurestaurant.njutakeout.blservice.qualification.IOSQualificationBlService;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IOSQualificationController {
    private final IOSQualificationBlService iosQualificationBlService;

    @Autowired
    public IOSQualificationController(IOSQualificationBlService iosQualificationBlService) {
        this.iosQualificationBlService = iosQualificationBlService;
    }

    @ApiOperation(value = "获取IOS是否许可", notes = "获取IOS是否许可(Admin)")
    @RequestMapping(value = "/getIOSQualification", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public boolean getIOSQualification() {
        return iosQualificationBlService.getIOSQualification();
    }

    @ApiOperation(value = "获取IOS是否许可", notes = "获取IOS是否许可(Admin)")
    @RequestMapping(value = "/getAppQualification", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public boolean getAppQualification() {
        return iosQualificationBlService.getIOSQualification();
    }

}

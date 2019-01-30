package njurestaurant.njutakeout.springcontroller.count;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.count.CountBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CountController {
    private final CountBlService countBlService;

    @Autowired
    public CountController(CountBlService countBlService) {
        this.countBlService = countBlService;
    }

    @ApiOperation(value = "获取各模块浏览次数(Admin)", notes = "获取各模块浏览次数(Admin)")
    @RequestMapping(value = "/getCount", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getCount() {
        return new ResponseEntity<>(countBlService.getCount(1), HttpStatus.OK);
    }

}

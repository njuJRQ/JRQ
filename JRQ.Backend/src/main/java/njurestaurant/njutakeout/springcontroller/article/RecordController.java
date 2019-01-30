package njurestaurant.njutakeout.springcontroller.article;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.article.RecordBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecordController {
    private final RecordBlService recordBlService;

    @Autowired
    public RecordController(RecordBlService recordBlService) {
        this.recordBlService = recordBlService;
    }
    @ApiOperation(value = "获取所有日志记录", notes = "获取所有日志记录")
    @RequestMapping(value = "/getRecordList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getRecordList(){
        return new ResponseEntity<>(recordBlService.getRecordList(), HttpStatus.OK);
    }
}

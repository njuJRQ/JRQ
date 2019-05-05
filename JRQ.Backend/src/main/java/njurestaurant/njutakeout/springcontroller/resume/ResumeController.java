package njurestaurant.njutakeout.springcontroller.resume;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.resume.ResumeBlService;
import njurestaurant.njutakeout.entity.resume.Resume;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.parameters.resume.ResumeParameters;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.resume.ResumeListResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/resume")
public class ResumeController {
    private ResumeBlService resumeBlService;

    @Autowired
    private ResumeController(ResumeBlService resumeBlService){
        this.resumeBlService=resumeBlService;
    }
    @ApiOperation(value = "新建简历", notes = "新建简历")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addResume(@RequestBody ResumeParameters parameters) throws NotExistException {
        Resume r=new Resume(parameters.getResumeId(),
                parameters.getUserId(),parameters.getTrueName(),parameters.getFace(),
                parameters.getfresh(),parameters.getDegree(),
                parameters.getExperience(),//实习时间
                (int)parameters.getAge(),
                parameters.getPositionId(),
                parameters.getPosition(),
                parameters.getEmail(),
                parameters.getPhone(),parameters.getExpectCity(),
                parameters.getLowWage(),
                parameters.getHighWage(),
                parameters.getResumeInternShips(),
                parameters.getResumeEducation());
        return new ResponseEntity<>(resumeBlService.add( r), HttpStatus.OK);
    }
    @ApiOperation(value = "修改简历", notes = "修改简历")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateResume(@RequestBody ResumeParameters parameters) throws NotExistException {
        Resume r=new Resume(parameters.getResumeId(),
                parameters.getUserId(),parameters.getTrueName(),parameters.getFace(),
                parameters.getfresh(),parameters.getDegree(),
                parameters.getExperience(),//实习时间
                (int)parameters.getAge(),
                parameters.getPositionId(),
                parameters.getPosition(),
                parameters.getEmail(),
                parameters.getPhone(),parameters.getExpectCity(),
                parameters.getLowWage(),
                parameters.getHighWage(),
                parameters.getResumeInternShips(),
                parameters.getResumeEducation());
        return new ResponseEntity<>(resumeBlService.update( r), HttpStatus.OK);
    }

    @ApiOperation(value = "修改简历", notes = "修改简历")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "resume ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteResume(@RequestParam(name = "id") String id) throws NotExistException {
        return new ResponseEntity<>(resumeBlService.deleteById(id), HttpStatus.OK);
    }
    @ApiOperation(value = "获取所有简历", notes = "获取所有简历")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResumeListResponce.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAll() {
        return new ResponseEntity<>(resumeBlService.getAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "根据用户id查找简历", notes = "根据定位查找求职信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "String")
    })
    @RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResumeListResponce.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findByUserId(@RequestParam(name = "userId") String userId) {
        return new ResponseEntity<>(resumeBlService.findByUserId(userId), HttpStatus.OK);
    }








}

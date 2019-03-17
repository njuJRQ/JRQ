package njurestaurant.njutakeout.springcontroller.partnership;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.partnership.PartnershipBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.exception.SystemException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.partnership.PartnershipListResponse;
import njurestaurant.njutakeout.response.partnership.PartnershipResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/partnership")
public class PartnershipController {
    private PartnershipBlService partnershipBlService;

    @Autowired
    public PartnershipController(PartnershipBlService partnershipBlService) {
        this.partnershipBlService = partnershipBlService;
    }

    @ApiOperation(value = "提交合伙信息", notes = "提交合伙信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "合伙信息id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> add(@RequestParam(name="linkMan")String linkMan,@RequestParam(name="phone")String phone,@RequestParam(name="agencyName")String agencyName,@RequestParam(name="identityInfo")String identityInfo,@RequestParam(name="type")String type,@RequestParam(name="img")List<MultipartFile> img) throws SystemException {
        List<String> images=new ArrayList<>();
        String base="JRQ.Backend/record/partnership/";
        for (MultipartFile image : img) {
            String[] temp = image.getOriginalFilename().split("\\.");
            String path =base+UUID.randomUUID().toString().replace("-", "").toLowerCase() + "."+temp[1];
            if (saveImg(path, image)) {
                images.add(path);
            }else{
                throw new SystemException();
            }
        }
//    s
        return new ResponseEntity<>(partnershipBlService.add(linkMan,phone,agencyName,identityInfo,type,images), HttpStatus.OK);

    }

    @ApiOperation(value = "通过id查找合伙信息", notes = "通过id查找合伙信息")
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PartnershipResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findById(@RequestParam(name="id")String id) throws NotExistException, SystemException {
        return new ResponseEntity<>(partnershipBlService.findById(id), HttpStatus.OK);

    }

    @ApiOperation(value = "获取所有合伙信息", notes = "获取所有合伙信息")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PartnershipListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAll(){
        return new ResponseEntity<>(partnershipBlService.getAll(), HttpStatus.OK);

    }



    private boolean saveImg(String path, MultipartFile image) {
        File newFile = new File(path);
        if (newFile.exists()) {
            newFile.delete();
        }
        newFile = new File(path);
        try {
            newFile.createNewFile();
            image.transferTo(new File(newFile.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

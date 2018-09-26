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
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class AdController {
    private final AdBlService adBlService;

    @Autowired
    public AdController(AdBlService adBlService) {
        this.adBlService = adBlService;
    }
    private static String headPath="";
    @ApiOperation(value = "获取广告图片", notes = "获取广告图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "face", value = "广告图片", required = true, dataType = "MultipartFile")
    })
    @RequestMapping(value = "/uploadAd", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public void uploadAd(@RequestParam("face")MultipartFile face){
        Map<String,Object> map= new HashMap<String,Object>();
        if(face.isEmpty()){
            map.put( "result", "error");
            map.put( "msg", "上传文件不能为空" );
        } else {

            // 获取文件名
            String fileName = face.getOriginalFilename();
            // 获取文件后缀

            // 用uuid作为文件名，防止生成的临时文件重复
            // MultipartFile to File
            //你的业务逻辑
            int bytesum = 0;
            int byteread = 0;
            InputStream inStream = null;    //读入原文件
            try {
                inStream = face.getInputStream();
                FileOutputStream fs = new FileOutputStream(fileName);
                headPath = fileName;
                byte[] buffer = new byte[20000000];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;            //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }


    @ApiOperation(value = "添加广告", notes = "添加广告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "link", value = "广告导向的链接", required = true, dataType = "String"),
            @ApiImplicitParam(name = "showPlace", value = "广告导向的链接", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addAd", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addAd(@RequestParam(name="link")String link,@RequestParam(name="showPlace")String showPlace) {
        File file = new File(headPath);
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String[] temp=headPath.split("\\.");
        String thePath="record/ad/image/"+uuid+"."+temp[1];
        String path="record/ad/image/"+uuid+"."+temp[1];
        File tempfile=new File(path);
        if (tempfile.exists() && tempfile.isFile()) {
            tempfile.delete();
        }
        int bytesum = 0;
        int byteread = 0;
        try {
            InputStream inStream =new FileInputStream(headPath);
            FileOutputStream fs = new FileOutputStream(path);
            byte[] buffer = new byte[20000000];
            while ( (byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;            //字节数 文件大小
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        headPath="";
        return new ResponseEntity<>(adBlService.addAd(thePath,link,showPlace), HttpStatus.OK);
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "showPlace", value = "广告ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getCheckedAd", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getCheckedAd(@RequestParam(name="showPlace")String showPlace) {
        return new ResponseEntity<>(adBlService.getCheckedAd(showPlace), HttpStatus.OK);
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
            @ApiImplicitParam(name = "link", value = "广告导向的链接", required = true, dataType = "String"),
            @ApiImplicitParam(name = "showPlace", value = "广告导向的链接", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updateAd", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateAd(@RequestParam(name="id")String id,@RequestParam(name="image")String image,@RequestParam(name="link")String link,@RequestParam(name="showPlace")String showPlace) throws NotExistException {
        return new ResponseEntity<>(adBlService.updateAd(id,image,link,showPlace), HttpStatus.OK);
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

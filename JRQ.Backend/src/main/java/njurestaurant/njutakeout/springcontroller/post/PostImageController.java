package njurestaurant.njutakeout.springcontroller.post;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.cardImage.CardImageBlService;
import njurestaurant.njutakeout.blservice.post.PostImageBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.parameters.cardImage.CardImageParameters;
import njurestaurant.njutakeout.parameters.post.PostImageParameters;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.job.JobCardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PostImageController {
    private final PostImageBlService postImageBlService;

    private PostImageController(PostImageBlService postImageBlService) {
        this.postImageBlService= postImageBlService;
    }

    @ApiOperation(value = "上传图片", notes = "上传图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postImage", value = "postImage"
                    , required = true, dataType = "MultipartFile")
    })
    @RequestMapping(value = "/uploadPostImage", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFeed(@RequestParam("image") MultipartFile image) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (image.isEmpty()) {
            map.put("result", "error");
            map.put("msg", "上传文件不能为空");
            return "上传文件不能为空";
        } else {

            // 获取文件名
            String fileName = image.getOriginalFilename();
            // 获取文件后缀

            // 用uuid作为文件名，防止生成的临时文件重复
            // MultipartFile to File
            //你的业务逻辑
            int bytesum = 0;
            int byteread = 0;
            InputStream inStream = null;    //读入原文件
            try {
                inStream = image.getInputStream();
                FileOutputStream fs = new FileOutputStream(fileName);
                byte[] buffer = new byte[200000000];
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
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            File file = new File(fileName);
            String[] temp = fileName.split("\\.");
            String thePath = "record/post/" + uuid + "." + temp[1];
            String path = "record/post/" + uuid + "." + temp[1];
            File tempfile = new File(path);
            if (tempfile.exists() && tempfile.isFile()) {
                tempfile.delete();
            }
            bytesum = 0;
            byteread = 0;
            try {
                inStream = new FileInputStream(fileName);
                FileOutputStream fs = new FileOutputStream(path);
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
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            return thePath;
        }
    }

    @ApiOperation(value = "添加海报图片", notes = "新建求职信息")
    @RequestMapping(value = "/addPostImage", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addCardImage(@RequestBody CardImageParameters parameters) throws NotExistException {

        return new ResponseEntity<>(postImageBlService.
                add(parameters.getImagePath()), HttpStatus.OK);
    }

    @ApiOperation(value = "删除图片名片", notes = "删除图片名片")
    @RequestMapping(value = "/deletePostImage", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteCardImage(@RequestBody PostImageParameters parameters) throws NotExistException {
        return new ResponseEntity<>(postImageBlService.
                delete(parameters.getImagePath()), HttpStatus.OK);
    }

    @ApiOperation(value = "根据id获取图片名片内容", notes = "根据id获取图片名片内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "job ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getAllPostImage", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = JobCardResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAllPostImage() throws NotExistException {
        return new ResponseEntity<>(postImageBlService.findAll(), HttpStatus.OK);
    }
}

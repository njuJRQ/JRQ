package njurestaurant.njutakeout.springcontroller.article.feed;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.article.feed.FeedBlService;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RestController
public class FeedController {
    private final FeedBlService feedBlService;
    @Autowired
    public FeedController(FeedBlService feedBlService) {
        this.feedBlService = feedBlService;
    }
    private static List<String> imagesPath=new ArrayList<>();

    @ApiOperation(value = "获取朋友圈图片", notes = "获取朋友圈图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "image", value = "朋友圈一张图片", required = true, dataType = "MultipartFile")
    })
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public void uploadHead(@RequestParam("image")MultipartFile image){
        Map<String,Object> map= new HashMap<String,Object>();
        if(image.isEmpty()){
            map.put( "result", "error");
            map.put( "msg", "上传文件不能为空" );
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
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            File file = new File(fileName);
            String[] temp=fileName.split("\\.");
            String thePath="record/feed/image/"+uuid+"."+temp[1];
            String path="JRQ.Backend/record/feed/image/"+uuid+"."+temp[1];
            File tempfile=new File(path);
            if (tempfile.exists() && tempfile.isFile()) {
                tempfile.delete();
            }
            file.renameTo(new File(path));
            imagesPath.add(thePath);

        }
    }
    @ApiOperation(value = "用户发布自己的圈子文章", notes = "用户发布自己的圈子文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "writerOpenid", value = "作者id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "发布日期", required = true, dataType = "String")
    })
    @RequestMapping(value = "/publishMyFeed", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> publishMyFeed(@RequestParam(name="content")String content, @RequestParam(name="writerOpenid")String writerOpenid, @RequestParam(name="date")String date) {
        ResponseEntity<Response> r= new ResponseEntity<>(feedBlService.publishMyFeed(content,imagesPath,writerOpenid,date), HttpStatus.OK);
        imagesPath.clear();
        return r;
    }

    @ApiOperation(value = "根据圈子文章ID获取全文", notes = "根据圈子文章ID获取全文")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "圈子文章ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getFeed", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getFeed(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(feedBlService.getFeed(id), HttpStatus.OK);
    }

    @ApiOperation(value = "获取圈子全部文章信息", notes = "获取圈子全部文章信息")
    @RequestMapping(value = "/getFeedList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getFeedList(){
        return new ResponseEntity<>(feedBlService.getFeedList(), HttpStatus.OK);
    }

    @ApiOperation(value = "用户获取自己发布过的文章摘要", notes = "用户获取自己发布过的文章摘要")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户的微信openid", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMyHistoryAbstractList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyHistoryAbstractList(@RequestParam(name="openid")String openid) throws NotExistException {
        return new ResponseEntity<>(feedBlService.getMyHistoryAbstractList(openid), HttpStatus.OK);
    }

    @ApiOperation(value = "用户更新自己发布的圈子文章信息", notes = "用户更新自己发布的圈子文章信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "圈子文章ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "发布日期", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updateMyFeed", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateMyFeed(@RequestParam(name="id")String id,@RequestParam(name="content")String content,@RequestParam(name="date")String date) throws NotExistException {
        ResponseEntity<Response> r=new ResponseEntity<>(feedBlService.updateMyFeed(id,content,imagesPath,date), HttpStatus.OK);
        imagesPath.clear();
        return r;
    }

    @ApiOperation(value = "用户根据圈子文章ID删除自己的圈子文章", notes = "用户根据圈子文章ID删除自己的圈子文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "圈子文章ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteMyFeed", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteMyFeed(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(feedBlService.deleteMyFeed(id), HttpStatus.OK);
    }

}
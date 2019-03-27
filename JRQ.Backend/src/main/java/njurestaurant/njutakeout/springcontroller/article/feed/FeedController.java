package njurestaurant.njutakeout.springcontroller.article.feed;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.article.feed.FeedBlService;
import njurestaurant.njutakeout.entity.article.Feed;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RestController
public class FeedController {
    private final FeedBlService feedBlService;
    @Autowired
    public FeedController(FeedBlService feedBlService) {
        this.feedBlService = feedBlService;
    }
    private static List<String> imagesPath=new ArrayList<>();

    @ApiOperation(value = "管理员上传圈子图片", notes = "管理员上传圈子图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "image", value = "image", required = true, dataType = "MultipartFile")
    })
    @RequestMapping(value = "/uploadFeed", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFeed(@RequestParam("image")MultipartFile image){
        Map<String,Object> map= new HashMap<String,Object>();
        if(image.isEmpty()){
            map.put( "result", "error");
            map.put( "msg", "上传文件不能为空" );
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
            String path="record/feed/image/"+uuid+"."+temp[1];
            File tempfile=new File(path);
            if (tempfile.exists() && tempfile.isFile()) {
                tempfile.delete();
            }
            bytesum = 0;
            byteread = 0;
            try {
                inStream =new FileInputStream(fileName);
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
            return thePath;
        }
    }

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
    public void uploadImage(@RequestParam("image")MultipartFile image){
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
            String[] temp=fileName.split("\\.");
            String thePath="";
            String path="";
            if(temp.length>2) {
                thePath = "JRQ.Backend/record/feed/image/" + uuid + "." + temp[3];
                path = "JRQ.Backend/record/feed/image/" + uuid + "." + temp[3];
            }else{
                thePath = "JRQ.Backend/record/feed/image/" + uuid + "." + temp[1];
                path = "JRQ.Backend/record/feed/image/" + uuid + "." + temp[1];
            }
            File tempfile=new File(path);
            if (tempfile.exists() && tempfile.isFile()) {
                tempfile.delete();
            }
            bytesum = 0;
            byteread = 0;
            try {
                inStream =new FileInputStream(fileName);
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
            imagesPath.add(thePath);
            File file=new File(fileName);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
        }
    }

    @ApiOperation(value = "用户发布自己的圈子文章", notes = "用户发布自己的圈子文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "writerOpenid", value = "作者id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/publishMyFeed", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> publishMyFeed(@RequestParam(name="linkMan")String linkMan, @RequestParam(name="phone")String phone,@RequestParam(name="agencyName")String agencyName,@RequestParam(name="projectRef")String projectRef,@RequestParam(name="projectInfo")String projectInfo,
                                                 @RequestParam(name="writerOpenid")String writerOpenid) {
        ResponseEntity<Response> r= new ResponseEntity<>(feedBlService.publishMyFeed(linkMan,phone,agencyName,projectRef,projectInfo,imagesPath,writerOpenid), HttpStatus.OK);
        imagesPath.clear();
        return r;
    }

    @ApiOperation(value = "用户发布自己的圈子文章", notes = "用户发布自己的圈子文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "images", value = "图片", required = true, dataType = "List<String>"),
            @ApiImplicitParam(name = "writerOpenid", value = "作者id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addFeed", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addFeed(@RequestParam(name="linkMan")String linkMan, @RequestParam(name="phone")String phone,@RequestParam(name="agencyName")String agencyName,@RequestParam(name="projectRef")String projectRef,@RequestParam(name="projectInfo")String projectInfo,
                                            @RequestParam(name="images")List<String> images,@RequestParam(name="writerOpenid")String writerOpenid) {
        ResponseEntity<Response> r= new ResponseEntity<>(feedBlService.publishMyFeed(linkMan,phone,agencyName,projectRef,projectInfo,images,writerOpenid), HttpStatus.OK);
        return r;
    }

    @ApiOperation(value = "根据圈子文章ID获取全文", notes = "根据圈子文章ID获取全文")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "圈子文章ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getFeed", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getFeed(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(feedBlService.getFeed(id), HttpStatus.OK);
    }

    @ApiOperation(value = "获取圈子全部含作者名字和头像的文章信息", notes = "获取圈子全部含作者名字和头像的文章信息")
    @RequestMapping(value = "/getFeedViewList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getFeedViewList() throws NotExistException {
        return new ResponseEntity<>(feedBlService.getFeedViewList(), HttpStatus.OK);
    }

    @ApiOperation(value = "获取某一篇圈子文章时间戳前的10篇文章", notes = "获取某一篇圈子文章时间戳前的10篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "圈子文章ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "圈子文章ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getFeedViewListBefore", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getFeedViewListBefore(
            @RequestParam(name="openid")String openid,
            @RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(feedBlService.getFeedViewListBefore(openid,id), HttpStatus.OK);
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


    @ApiOperation(value = "管理员更新圈子文章内容", notes = "管理员更新圈子文章内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "圈子ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "圈子文本", required = true, dataType = "String"),
            @ApiImplicitParam(name = "images", value = "圈子图片", required = true, dataType = "List<String>")
    })
    @RequestMapping(value = "/updateFeed", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateFeed(@RequestParam(name="id")String id,@RequestParam(name="linkMan")String linkMan,@RequestParam(name="phone")String phone,@RequestParam(name="agencyName")String agencyName,@RequestParam(name="projectRef")String projectRef,@RequestParam(name="projectInfo")String projectInfo,
                                               @RequestParam(name="images")List<String> images) throws NotExistException {
        return new ResponseEntity<>(feedBlService.updateFeed(id,linkMan,phone,agencyName,projectRef,projectInfo,images), HttpStatus.OK);
    }

    @ApiOperation(value = "根据id删除圈子", notes = "根据id删除圈子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "圈子文章ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteFeed", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteFeed(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(feedBlService.deleteFeed(id), HttpStatus.OK);
    }

    @ApiOperation(value = "用户获取自己发布过的文章摘要", notes = "用户获取自己发布过的文章摘要")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "myOpenid", value = "用户的微信openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "otherOpenid", value = "要查看的人的微信openid", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getUserHistoryAbstractList", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getUserHistoryAbstractList(@RequestParam(name="myOpenid")String myOpenid,@RequestParam(name="otherOpenid")String otherOpenid) throws NotExistException {
        return new ResponseEntity<>(feedBlService.getUserHistoryAbstractList(myOpenid,otherOpenid), HttpStatus.OK);
    }

    @ApiOperation(value = "用户更新自己发布的圈子文章信息", notes = "用户更新自己发布的圈子文章信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "圈子文章ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updateMyFeed", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateMyFeed(@RequestParam(name="id")String id,@RequestParam(name="linkMan")String linkMan,@RequestParam(name="phone")String phone,@RequestParam(name="agencyName")String agencyName,@RequestParam(name="projectRef")String projectRef,@RequestParam(name="projectInfo")String projectInfo) throws NotExistException {
        ResponseEntity<Response> r=new ResponseEntity<>(feedBlService.updateMyFeed(id,linkMan,phone,agencyName,projectRef,projectInfo,imagesPath), HttpStatus.OK);
        imagesPath.clear();
        return r;
    }

    @ApiOperation(value = "用户根据圈子文章ID删除自己的圈子文章", notes = "用户根据圈子文章ID删除自己的圈子文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "圈子文章ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteMyFeed", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteMyFeed(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(feedBlService.deleteMyFeed(id), HttpStatus.OK);
    }

    @ApiOperation(value = "根据圈子文章ID获取含作者名字和头像的全文", notes = "根据圈子文章ID获取含作者名字和头像的全文")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "圈子文章ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getFeedView", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getFeedView(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(feedBlService.getFeedView(id), HttpStatus.OK);
    }

    @ApiOperation(value = "获取10条项目按条件查询", notes = "获取10条项目按条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kind", value = "查询条件", required = true, dataType = "String"),
            @ApiImplicitParam(name = "openid", value = "用户openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "圈子ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getProjectListBeforeByKind", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getProjectListBeforeByKind(
            @RequestParam(name="kind")String kind,
            @RequestParam(name="openid")String openid,
            @RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(feedBlService.getFeedListBeforeByKind(kind,openid,id), HttpStatus.OK);
    }

    @ApiOperation(value = "通过标题模糊搜索项目", notes = "通过标题模糊搜索项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "condition", value = "搜索条件", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getProjectListByCondition", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getProjectListByCondition(
            @RequestParam(name="openid")String openid,
            @RequestParam(name="condition")String condition) throws NotExistException {
        return new ResponseEntity<>(feedBlService.getFeedListByCondition(openid,condition), HttpStatus.OK);
    }

    @ApiOperation(value = "获取我发布的项目列表", notes = "获取我发布的项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户openid", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMyProjectListOrderByTime", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyProjectListOrderByTime(
            @RequestParam(name="openid")String openid) throws NotExistException {
        return new ResponseEntity<>(feedBlService.getMyFeedList(openid), HttpStatus.OK);
    }
}

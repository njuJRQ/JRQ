package njurestaurant.njutakeout.bl.upload;

import njurestaurant.njutakeout.blservice.upload.UploadService;
import njurestaurant.njutakeout.util.PathUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    private final static String TEMP_PATH = PathUtil.getTmpPath();
    private static final long EXPIRATION = Long.MAX_VALUE;

    @Value("${oos.accessKey}")
    private String accessKey;
    @Value("${oos.secretKey}")
    private String secretKey;
    @Value("${oos.endPoint}")
    private String endPoint;
    @Value("${oos.bucketName}")
    private String bucketName;

    @Override
    public String upload(MultipartFile multipartFile) throws IOException, URISyntaxException {

            //保存到临时文件
//        File file = new File(TEMP_PATH);
//
//        String key= UUID.randomUUID().toString();
//
//        OSSClient ossClient = new OSSClient(endPoint, accessKey, secretKey);
//
//        ossClient.putObject(bucketName, key, file);
//
//        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
//            //生成共享地址
//        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//        return url.toURI().toString();

        Map<String,Object> map= new HashMap<String,Object>();
        if(multipartFile.isEmpty()){
            map.put( "result", "error");
            map.put( "msg", "上传文件不能为空" );
            return "上传文件不能为空";
        } else {

            // 获取文件名
            String fileName = multipartFile.getOriginalFilename();
            // 获取文件后缀

            // 用uuid作为文件名，防止生成的临时文件重复
            // MultipartFile to File
            //你的业务逻辑
            int bytesum = 0;
            int byteread = 0;
            InputStream inStream = null;    //读入原文件
            try {
                inStream = multipartFile.getInputStream();
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
            String thePath = "record/" + uuid + "." + temp[1];
            String path = "record/" + uuid + "." + temp[1];
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
}

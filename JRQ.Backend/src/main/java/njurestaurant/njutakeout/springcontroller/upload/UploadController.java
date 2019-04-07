package njurestaurant.njutakeout.springcontroller.upload;

import io.swagger.annotations.ApiOperation;
import njurestaurant.njutakeout.blservice.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class UploadController {
    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) throws IOException, URISyntaxException {
        String filePath=uploadService.upload(file);
        return filePath;
    }
}

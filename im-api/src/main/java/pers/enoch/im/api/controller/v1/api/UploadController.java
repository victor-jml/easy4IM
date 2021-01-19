package pers.enoch.im.api.controller.v1.api;

import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.enoch.im.api.service.QCloudCosService;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.utils.Result;

/**
 * @Author: zy
 * @Date: 2021/1/17 22:06
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping(value = "/v1/api/upload")
public class UploadController {
    @Autowired
    private QCloudCosService qCloudCosService;


    @PostMapping(value = "/image")
    @ResponseBody
    public Result uploadImage(@RequestPart("file") MultipartFile file){
        String url = qCloudCosService.uploadImage(file);
        return Strings.isEmpty(url) ? Result.success(ResultEnum.SERVER_ERROR,System.currentTimeMillis())
                : Result.success(ResultEnum.SUCCESS,url);
    }

}

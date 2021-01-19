package pers.enoch.im.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.enoch.im.common.utils.QCloudCosUtils;

import java.io.File;

/**
 * @Author: zy
 * @Date: 2021/1/17 21:18
 * @Description: Cos Service (upload Images)
 */
@Slf4j
@Service
public class QCloudCosService {

    @Autowired
    private QCloudCosUtils qCloudCosUtils;

    /**
     * user upload image to cos
     * @param multipartFile
     * @return upload success image url
     */
        public String uploadImage(MultipartFile multipartFile) {
        return qCloudCosUtils.upload(multipartFile);
    }

    public String uploadImage(File file) {
        return qCloudCosUtils.upload(file);
    }
}

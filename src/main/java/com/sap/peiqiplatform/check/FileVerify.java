package com.sap.peiqiplatform.check;

import cn.hutool.core.collection.CollUtil;
import com.sap.peiqiplatform.constant.CommonConstants;
import com.sap.peiqiplatform.entity.bo.ParamCheckVo;
import com.sap.peiqiplatform.entity.dto.request.InvoluntaryDeployEntity;
import com.sap.peiqiplatform.entity.dto.request.VoluntaryDeployEntity;
import com.sap.peiqiplatform.entity.dto.response.UploadRes;
import com.sap.peiqiplatform.exception.APIException;
import com.sap.peiqiplatform.utils.DirectoryUtil;
import com.sap.peiqiplatform.utils.FileUtil;
import com.sap.peiqiplatform.utils.LOGUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import static com.sap.peiqiplatform.constant.CommonConstants.ONE;
import static com.sap.peiqiplatform.constant.DirectoryConstants.*;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName FileVerify.java
 * @Description TODO
 * @createTime 2022-08-21  14:33:00
 */
@Slf4j
public class FileVerify {
    public static ParamCheckVo checkUploadFile(MultipartFile file) {
        ParamCheckVo paramCheckVo = ParamCheckVo.checkPass();
        String fileSuffix = FileUtil.getFileSuffix(Objects.requireNonNull(file.getOriginalFilename()));
        if (!Objects.equals(fileSuffix, MTAEXT) && !Objects.equals(fileSuffix, MTAR)) {
            paramCheckVo.setMistaken(true);
            paramCheckVo.setCheckMsg(" you can only upload `.mtar` or `.mtaext` file !");
            return paramCheckVo;
        }
        Long sizeOfMB = FileUtil.byte2MB(file.getSize());
        LOGUtil.printLog(log, String.format("the size of file is %s MB ", sizeOfMB));
        if (sizeOfMB > CommonConstants.MAX_UPLOAD_FILE ){
            paramCheckVo.setMistaken(true);
            paramCheckVo.setCheckMsg(" the file size is to big , plz batch upload !");
            return paramCheckVo;
        }
        return paramCheckVo;
    }

    public static void checkFileListNum(Object obj){

        if (obj instanceof VoluntaryDeployEntity){
            VoluntaryDeployEntity voluntaryDeployEntity = (VoluntaryDeployEntity) obj;
            ArrayList<UploadRes> mtarFileList = voluntaryDeployEntity.getMtarFileList();
            ArrayList<UploadRes> extensionFileList = voluntaryDeployEntity.getExtensionFileList();

            if (CollUtil.isEmpty(mtarFileList) || mtarFileList.size() > ONE){
                throw new APIException("`mtar` file can not empty and can only upload one !");
            }
            if (CollUtil.isEmpty(extensionFileList) || extensionFileList.size() > ONE){
                throw new APIException("`mtaext` file can not empty and  can only upload one !");
            }
        }
        else if (obj instanceof InvoluntaryDeployEntity){
            InvoluntaryDeployEntity involuntaryDeployEntity = (InvoluntaryDeployEntity) obj;
            ArrayList<UploadRes> extensionFileList = involuntaryDeployEntity.getExtensionFileList();
//            String mtaExtText = involuntaryDeployEntity.getMtaExtText();
            if (CollUtil.isEmpty(extensionFileList) || extensionFileList.size() > ONE){
                throw new APIException("`mtaext` file can not empty and  can only upload one !");
            }
        }

    }

    public static void checkFileExist(String fileUrl){
        String relativeFileUrl = DirectoryUtil.getRelativePathByFileUrl(fileUrl);
        File file = new File(relativeFileUrl);
        if(!file.exists()){
            log.warn(" file is not exist , fileUrl is {} ",fileUrl);
            throw new APIException("file is not exist , fileUrl is " + fileUrl);
        }
    }
}

package com.sap.peiqiplatform.utils;

import cn.hutool.core.util.StrUtil;
import com.sap.peiqiplatform.check.FileVerify;
import com.sap.peiqiplatform.constant.DownloadConstants;
import com.sap.peiqiplatform.exception.APIException;
import com.sap.peiqiplatform.nums.AppNameEnum;
import com.sap.peiqiplatform.utils.abs.URLResourceTemplate;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sap.peiqiplatform.constant.DirectoryConstants.SLASH;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName GetInternetResourceUtil.java
 * @Description TODO
 * @createTime 2022-08-27  15:08:00
 */
@Slf4j
@Component
public class GetInternetResourceUtil extends URLResourceTemplate {
    @Autowired
    private RestTemplate restTemplate;

    private static final String PREFIX_VERSION = "1.";



    @Override
    public List<String> getServerInfo() {
        return parseObject(restTemplate.getForObject(DownloadConstants.IA_NODE_ASSEMBLY,String.class),PREFIX_VERSION);
    }

    @Override
    public List<String> getICAClientNodeInfo() {
        return parseObject(restTemplate.getForObject(DownloadConstants.ICA_CLIENT_NODE_ASSEMBLY,String.class),PREFIX_VERSION);
    }

    @Override
    public List<String> getWorkSpaceInfo() {
        return parseObject(restTemplate.getForObject(DownloadConstants.WORK_SPACE_ASSEMBLY,String.class),PREFIX_VERSION);
    }

    @Override
    public void downloadFileByAppNameAndVersion(String appName, String version,String filePath) {
        String specPrefix = getSpecPrefixByAppName(appName);
        String specForDown = jointSpecByVersion(specPrefix, version);
        String mtarFileName = FileUtil.jointDownloadFileName(appName, version);
        String pathAndName = DirectoryUtil.getAllPathByPathAndName(filePath, mtarFileName);

        try {
            URL url = new URL(specForDown);
            ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
            try (FileOutputStream fileOutputStream = new FileOutputStream(new File(pathAndName))) {
                fileOutputStream.getChannel()
                        .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            FileVerify.checkFileExist(pathAndName);
        }
    }

    private String getSpecPrefixByAppName(String appName){
        if (Objects.equals(appName, AppNameEnum.IA_CLIENT_NODE.getDescription())){
            return DownloadConstants.ICA_CLIENT_NODE_ASSEMBLY;
        }else if (Objects.equals(appName,AppNameEnum.SERVER.getDescription())){
            return DownloadConstants.IA_NODE_ASSEMBLY;
        }else if (Objects.equals(appName,AppNameEnum.WORKSPACE.getDescription())){
            return DownloadConstants.WORK_SPACE_ASSEMBLY;
        }
        throw new APIException("can not found spec by application name");
    }

    private String jointSpecByVersion(String specPrefix, String version){
        String assemblySpec = specPrefix + SLASH + version;
        String restTemplateForObject = restTemplate.getForObject(assemblySpec, String.class);

        return "";
    }

    private List<String> parseObject(String object,String filter){
        log.info(" start parse html object to list .");
        ArrayList<String> list = new ArrayList<>();
        Document doc = Jsoup.parse(object);
        Elements elements = doc.getElementsByTag("a");

        for (Element element : elements) {
            if (element.hasText()){
                list.add(element.text());
            }
        }

        if (StrUtil.isNotBlank(filter)){
           return list.stream()
                   .filter(f -> f.startsWith(filter))
                   .collect(Collectors.toList());
        }
         return list;
    }
}

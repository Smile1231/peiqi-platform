package com.sap.peiqiplatform.utils.abs;

import java.util.List;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName URLResourceTemplate.java
 * @Description TODO
 * @createTime 2022-08-27  15:08:00
 */
public abstract class URLResourceTemplate {

    public abstract List<String> getServerInfo();

    public abstract List<String> getICAClientNodeInfo();

    public abstract List<String> getWorkSpaceInfo();

    public abstract void downloadFileByAppNameAndVersion(String appName,String version,String filePath);

}

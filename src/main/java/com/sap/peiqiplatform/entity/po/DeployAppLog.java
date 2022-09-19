package com.sap.peiqiplatform.entity.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName deploy_app_log
 */
@Data
public class DeployAppLog implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * id in deploy_app_record
     */
    private Integer deployRecordId;

    /**
     * 0 -- success  1 -- failed
     */
    private Integer status;

    /**
     * 
     */
    private String logInfo;

    /**
     * 
     */
    private String logFilePath;

    /**
     * 
     */
    private String takeTime;

    /**
     * 
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}
package com.sap.peiqiplatform.entity.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName deploy_app_voluntary_record
 */
@Data
public class DeployAppVoluntaryRecord implements Serializable {
    /**
     * primary key
     */
    private Integer id;

    /**
     * 0 -- Involuntary upload  1 -- voluntary upload
     */
    private Integer mtarUploadFlag;

    /**
     * uid in upload file info
     */
    private String mtarFileUid;

    /**
     * uid in upload file info
     */
    private String extensionFileUid;

    /**
     * 
     */
    private String apiEndPoint;

    /**
     * 
     */
    private String orgName;

    /**
     * 
     */
    private String spaceName;

    /**
     * 
     */
    private String deployAccount;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String createBy;

    private static final long serialVersionUID = 1L;
}
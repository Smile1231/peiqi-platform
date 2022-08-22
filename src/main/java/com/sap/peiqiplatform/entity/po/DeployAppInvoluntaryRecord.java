package com.sap.peiqiplatform.entity.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * deploy_app_involuntary_record
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeployAppInvoluntaryRecord implements Serializable {
    /**
     * primary key
     */
    private Integer id;

    /**
     * 0 -- Involuntary upload  1 -- voluntary upload
     */
    private Integer mtarUploadFlag;

    /**
     * id in app_table 
     */
    private Integer applicationId;

    private String appVersion;

    private String apiEndPoint;

    private String orgName;

    private String spaceName;

    /**
     * uid in upload file info
     */
    private Integer extensionFileUid;

    private String deployAccount;

    private Date createTime;

    private String createBy;

    private static final long serialVersionUID = 1L;
}
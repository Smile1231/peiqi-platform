package com.sap.peiqiplatform.entity.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName upload_file_info
 */
@Data
public class UploadFileInfo implements Serializable {
    /**
     * primary key
     */
    private Integer id;

    /**
     * 0 - mtar ；1 - extension ; 8 -- example
     */
    private Integer fileType;

    /**
     * 
     */
    private String fileUid;

    /**
     * 
     */
    private String fileName;

    /**
     * 
     */
    private String fileUrl;

    /**
     * 
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}
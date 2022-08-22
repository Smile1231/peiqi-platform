package com.sap.peiqiplatform.entity.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * app_table
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppTable implements Serializable {
    /**
     * primary key
     */
    private Integer id;

    private String applicationName;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private static final long serialVersionUID = 1L;
}
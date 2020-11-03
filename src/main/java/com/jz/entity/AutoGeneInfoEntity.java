package com.jz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * (AutoGeneInfo)实体类
 *
 * @author xiaoG
 * @since 2020-10-25 17:46:54
 */
@Data
public class AutoGeneInfoEntity {

    private Integer id;

    /**
     * 姓名
     */
    private String name;


    private Boolean sex;


    private Integer age;


    private String workInfo;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


}
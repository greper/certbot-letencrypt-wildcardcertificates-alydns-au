package com.ssl.manager.modules.ssl.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2020/9/17$
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sm_domain")
public class Domain {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String domain;
    @TableField( fill = FieldFill.INSERT)
    private Date createTime;
    @TableField( fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private Date lastGenerateTime;
    private Date expiresTime;
    private Integer status;

}

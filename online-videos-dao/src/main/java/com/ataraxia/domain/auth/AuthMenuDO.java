package com.ataraxia.domain.auth;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ataraxia
 * @create 2022/4/21 07:30
 * @description 权限控制-页面访问表
 * @TableName t_auth_menu
 */
@ApiModel(value = "页面访问实体类")
@TableName("t_auth_menu")
public class AuthMenuDO implements Serializable {

    /**
    * 主键id
    */
    @ApiModelProperty("主键id")
    private Long id;

    /**
    * 菜单项目名称
    */
    @ApiModelProperty("菜单项目名称")
    private String name;

    /**
    * 唯一编码
    */
    @ApiModelProperty("唯一编码")
    private String code;

    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
    * 更新时间
    */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AuthMenuDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

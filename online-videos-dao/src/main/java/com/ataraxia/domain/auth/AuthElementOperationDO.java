package com.ataraxia.domain.auth;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ataraxia
 * @create 2022/4/21 07:30
 * @description 权限控制--页面元素操作表
 * @TableName t_auth_element_operation
 */
@ApiModel(value = "页面元素操作实体类")
@TableName("t_auth_element_operation")
public class AuthElementOperationDO implements Serializable {

    /**
    * 主键id
    */
    @ApiModelProperty("主键id")
    private Long id;

    /**
    * 页面元素名称
    */
    @ApiModelProperty("页面元素名称")
    private String elementName;

    /**
    * 页面元素唯一编码
    */
    @ApiModelProperty("页面元素唯一编码")
    private String elementCode;

    /**
    * 操作类型：0可点击  1可见
    */
    @ApiModelProperty("操作类型：0可点击  1可见")
    private String operationType;

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

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementCode() {
        return elementCode;
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
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
        return "AuthElementOperationDO{" +
                "id=" + id +
                ", elementName='" + elementName + '\'' +
                ", elementCode='" + elementCode + '\'' +
                ", operationType='" + operationType + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

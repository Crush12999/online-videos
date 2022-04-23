package com.ataraxia.domain.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ataraxia
 * @create 2022/4/21 07:30
 * @description 权限控制-角色与元素操作关联表
 * @TableName t_auth_role_element_operation
 */
@ApiModel(value = "角色与元素操作关联实体类")
@TableName("t_auth_role_element_operation")
public class AuthRoleElementOperationDO implements Serializable {

    /**
    * 主键id
    */
    @ApiModelProperty("主键id")
    private Long id;

    /**
    * 角色id
    */
    @ApiModelProperty("角色id")
    private Long roleId;

    /**
    * 元素操作id
    */
    @ApiModelProperty("元素操作id")
    private Long elementOperationId;

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

    /**
     * 操作权限，放入冗余字段是为了查表的时候关联查询拿到，防止两次查询
     */
    @TableField(exist = false)
    @ApiModelProperty("操作权限")
    private AuthElementOperationDO authElementOperation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getElementOperationId() {
        return elementOperationId;
    }

    public void setElementOperationId(Long elementOperationId) {
        this.elementOperationId = elementOperationId;
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

    public AuthElementOperationDO getAuthElementOperation() {
        return authElementOperation;
    }

    public void setAuthElementOperation(AuthElementOperationDO authElementOperation) {
        this.authElementOperation = authElementOperation;
    }

    @Override
    public String toString() {
        return "AuthRoleElementOperationDO{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", elementOperationId=" + elementOperationId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

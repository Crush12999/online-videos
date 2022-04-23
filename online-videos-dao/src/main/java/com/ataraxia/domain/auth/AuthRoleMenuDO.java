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
 * @description 权限控制-角色页面菜单关联表
 * @TableName t_auth_role_menu
 */
@ApiModel(value = "角色页面菜单关联实体类")
@TableName("t_auth_role_menu")
public class AuthRoleMenuDO implements Serializable {

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
    * 页面菜单id
    */
    @ApiModelProperty("页面菜单id")
    private Long menuId;

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
     * 页面访问权限
     */
    @ApiModelProperty("页面访问权限")
    @TableField(exist = false)
    private AuthMenuDO authMenu;

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

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
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

    public AuthMenuDO getAuthMenu() {
        return authMenu;
    }

    public void setAuthMenu(AuthMenuDO authMenu) {
        this.authMenu = authMenu;
    }

    @Override
    public String toString() {
        return "AuthRoleMenuDO{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", menuId=" + menuId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

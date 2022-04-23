package com.ataraxia.domain;

import com.ataraxia.domain.auth.AuthRoleElementOperationDO;
import com.ataraxia.domain.auth.AuthRoleMenuDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Ataraxia
 * @create 2022/4/23 13:49
 * @description 用户权限实体类
 */
@ApiModel(value = "用户权限实体类")
public class UserAuthDTO {

    /**
     * 页面元素操作权限
     */
    @ApiModelProperty(value = "页面元素操作权限列表")
    List<AuthRoleElementOperationDO> roleElementOperationList;

    /**
     * 菜单权限
     */
    @ApiModelProperty(value = "页面菜单权限列表")
    List<AuthRoleMenuDO> roleMenuList;

    public List<AuthRoleElementOperationDO> getRoleElementOperationList() {
        return roleElementOperationList;
    }

    public void setRoleElementOperationList(List<AuthRoleElementOperationDO> roleElementOperationList) {
        this.roleElementOperationList = roleElementOperationList;
    }

    public List<AuthRoleMenuDO> getRoleMenuList() {
        return roleMenuList;
    }

    public void setRoleMenuList(List<AuthRoleMenuDO> roleMenuList) {
        this.roleMenuList = roleMenuList;
    }

    @Override
    public String toString() {
        return "UserAuthDTO{" +
                "roleElementOperationList=" + roleElementOperationList +
                ", roleMenuList=" + roleMenuList +
                '}';
    }
}

package com.ataraxia.service.impl;

import com.ataraxia.domain.auth.AuthMenuDO;
import com.ataraxia.mapper.auth.AuthMenuMapper;
import com.ataraxia.service.AuthMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author chuchen
* @description 针对表【t_auth_menu(权限控制-页面访问表)】的数据库操作Service实现
* @createDate 2022-04-23 14:22:03
*/
@Service
public class AuthMenuServiceImpl extends ServiceImpl<AuthMenuMapper, AuthMenuDO>
    implements AuthMenuService {

}





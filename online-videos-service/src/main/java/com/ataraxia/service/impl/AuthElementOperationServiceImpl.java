package com.ataraxia.service.impl;

import com.ataraxia.domain.auth.AuthElementOperationDO;
import com.ataraxia.mapper.auth.AuthElementOperationMapper;
import com.ataraxia.service.AuthElementOperationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Ataraxia
 * @create 2022/4/23 14:16
 * @description 页面元素操作权限服务层接口实现
 */
@Service
public class AuthElementOperationServiceImpl extends ServiceImpl<AuthElementOperationMapper, AuthElementOperationDO>
        implements AuthElementOperationService {
}

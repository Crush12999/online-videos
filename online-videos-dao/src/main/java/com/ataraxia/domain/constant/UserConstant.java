package com.ataraxia.domain.constant;

/**
 * @author Ataraxia
 * @create 2022/4/21 11:09
 * @description 用户基本信息常量
 */
public interface UserConstant {

    /**
     * 性别男
     */
    String GENDER_MALE = "0";

    /**
     * 性别女
     */
    String GENDER_FEMALE = "1";

    /**
     * 性别未知
     */
    String GENDER_UNKNOWN = "2";

    /**
     * 默认出生日期
     */
    String DEFAULT_BIRTH = "1999-10-01";

    /**
     * 默认昵称
     */
    String DEFAULT_NICKNAME = "萌新";

    /**
     * 默认分组 type值为 2
     */
    String USER_FOLLOWING_GROUP_TYPE_DEFAULT = "2";

    /**
     * 用户自定义分组 type值为 3
     */
    String USER_FOLLOWING_GROUP_TYPE_USER = "3";


    String USER_FOLLOWING_GROUP_ALL_NAME = "全部关注";
}

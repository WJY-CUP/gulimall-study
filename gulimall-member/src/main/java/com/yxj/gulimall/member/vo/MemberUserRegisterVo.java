package com.yxj.gulimall.member.vo;

import lombok.Data;


/**
 * 与Auth-server中的UserRegisterVo只差一个验证码和JSR303数据校验，
 * 因为注册表单数据是从auth-server传过来的，肯定经过数据校验了，无需再次校验
 * @author yaoxinjia
 * @email 894548575@qq.com
 */
@Data
public class MemberUserRegisterVo {

    private String userName;

    private String password;

    private String phone;

}

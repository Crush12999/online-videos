package com.ataraxia.util;

import com.ataraxia.domain.exception.ConditionException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Ataraxia
 * @create 2022/4/21 12:45
 * @description Token工具类
 */
@Component
public class TokenUtil {

    /**
     * 签发者
     */
    private static final String ISSUER = "Ataraxia";

    /**
     * 令牌创建
     * @param userId 签发对象
     * @return Token令牌
     * @throws Exception RSA工具类异常
     */
    public static String generateToken(Long userId) throws Exception {
        // 使用RSA256算法加密
        Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
        Calendar calendar = Calendar.getInstance();
        // 当前时间
        calendar.setTime(new Date());
        // 超时时间
        calendar.add(Calendar.MINUTE, 30);

        return JWT.create()
                // 唯一身份标识
                .withKeyId(String.valueOf(userId))
                // 签发者
                .withIssuer(ISSUER)
                // 过期时间
                .withExpiresAt(calendar.getTime())
                // 签名
                .sign(algorithm);
    }

    public static Long verifyToken(String token) {
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String userId = jwt.getKeyId();
            return Long.valueOf(userId);
        } catch (TokenExpiredException e) {
            throw new ConditionException("555", "token过期！");
        } catch (Exception e) {
            throw new ConditionException("非法用户token！");
        }
    }

    public static String generateRefreshToken(Long userId) throws Exception {
        // 使用RSA256算法加密
        Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
        Calendar calendar = Calendar.getInstance();
        // 当前时间
        calendar.setTime(new Date());
        // 超时时间
        calendar.add(Calendar.DAY_OF_MONTH, 7);

        return JWT.create()
                // 唯一身份标识
                .withKeyId(String.valueOf(userId))
                // 签发者
                .withIssuer(ISSUER)
                // 过期时间
                .withExpiresAt(calendar.getTime())
                // 签名
                .sign(algorithm);
    }
}

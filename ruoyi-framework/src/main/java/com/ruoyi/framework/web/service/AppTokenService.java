package com.ruoyi.framework.web.service;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.model.AppLoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
public class AppTokenService {
    private static final Logger log = LoggerFactory.getLogger(AppTokenService.class);

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌密钥
    @Value("${token.secret}")
    private String secret;

    @Value("${token.expireTime}")
    // 令牌有效期（默认30分钟）
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Resource
    private RedisCache redisCache;

    /**
     * 获取用户身份信息
     * @param request
     * @return
     */
    public AppLoginUser getAppLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            try {
                Claims claims = parseToken(token);
                // 解析对应的权限以及用户信息
                String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
                String userKey = getTokenKey(uuid);
                AppLoginUser appLoginUser = redisCache.getCacheObject(userKey);
                return appLoginUser;
            } catch (Exception e) {
                log.error("获取用户信息异常'{}'", e.getMessage());
            }
        }
        return null;
    }

    /**
     * 设置用户身份信息
     * @param appLoginUser
     */
    public void setLoginUser(AppLoginUser appLoginUser) {
        if (StringUtils.isNotNull(appLoginUser) && StringUtils.isNotEmpty(appLoginUser.getToken())) {
            refreshToken(appLoginUser);
        }
    }

    /**
     * 删除用户身份信息
     * @param token
     */
    public void delAppLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisCache.deleteObject(userKey);
        }
    }

    /**
     * 创建令牌
     * @param appLoginUser
     * @return
     */
    public String createToken(AppLoginUser appLoginUser) {
        String token = IdUtils.fastUUID();
        appLoginUser.setToken(token);
        setUserAgent(appLoginUser);
        refreshToken(appLoginUser);
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param appLoginUser
     * @return 令牌
     */
    public void verifyToken(AppLoginUser appLoginUser)
    {
        long expireTime = appLoginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
        {
            refreshToken(appLoginUser);
        }
    }

    /**
     * 从数据声明生成令牌
     * @param claims
     * @return
     */
    private String createToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 设置用户代理信息
     * @param appLoginUser
     */
    public void setUserAgent(AppLoginUser appLoginUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr();
        appLoginUser.setIpaddr(ip);
        appLoginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        appLoginUser.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 刷新令牌有效期
     * @param appLoginUser
     */
    public void refreshToken(AppLoginUser appLoginUser) {
        appLoginUser.setLoginTime(System.currentTimeMillis());
        appLoginUser.setExpireTime(appLoginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将appLoginUser缓存
        String userKey = getTokenKey(appLoginUser.getToken());
        redisCache.setCacheObject(userKey, appLoginUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 从令牌中获取数据声明
     * @param token
     * @return
     */
    private Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 获取请求token
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid) {
        return CacheConstants.LOGIN_TOKEN_KEY + uuid;
    }
}

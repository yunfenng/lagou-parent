package com.lagou.edu.config;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author Jaa
 * @Date 2022/8/9 7:29
 * @Description
 */
@Component
public class LagouAccessTokenConvertor extends DefaultAccessTokenConverter {

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication oAuth2Authentication = super.extractAuthentication(map);
        oAuth2Authentication.setDetails(map); // 将map放入认证对象中, 认证对象在controller中可以获取到
        return oAuth2Authentication;
    }
}

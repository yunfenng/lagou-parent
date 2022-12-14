package com.lagou.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

/**
 * 当前类为Oauth2 server的配置类（需要继承特定的⽗类AuthorizationServerConfigurerAdapter）
 */
@Configuration
@EnableAuthorizationServer // 开启认证服务器功能
public class OAuthServerConfigure extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LagouAccessTokenConvertor lagouAccessTokenConvertor;

    private String sign_key = "lagou123"; // jwt签名密钥

    /**
     *  认证服务器最终是以api接⼝的⽅式对外提供服务（校验合法性并⽣成令牌、校验令牌等）
     *  那么，以api接⼝⽅式对外的话，就涉及到接⼝的访问权限，我们需要在这⾥进⾏必要的配置
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        // 相当于打开endpoints 访问接⼝的开关，这样的话后期我们能够访问该接⼝
        security
                // 允许客户端表单认证
                .allowFormAuthenticationForClients()
                // 开启端⼝/oauth/token_key的访问权限（允许）
                .tokenKeyAccess("permitAll()")
                // 开启端⼝/oauth/check_token的访问权限（允许）
                .checkTokenAccess("permitAll()");

    }

    /**
     * 客户端详情配置，
     *   ⽐如client_id，secret
     * 当前这个服务就如同QQ平台，拉勾⽹作为客户端需要qq平台进⾏登录授权认证等，提前需要到QQ平台注册，QQ平台会给拉勾⽹
     * 颁发client_id等必要参数，表明客户端是谁
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);

        /*// 从内存中加载客户端详情
        clients.inMemory() // 客户端信息存储在什么地方，可以存在内存中，可以存在数据库里
                .withClient("client_lagou") // 添加一个client配置，指定其client_id
                .secret("abcxyz")                   // 指定客户端的密码/安全码
                .resourceIds("autodeliver")         // 指定客户端所能访问的id清单，此处的资源id是需要在具体的资源服务器上也配置⼀样
                // 认证类型/令牌颁发模式，可以配置多个在这⾥，但是不⼀定都⽤，具体使⽤哪种⽅式颁发token，需要客户端调⽤的时候传递参数指定
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("all"); // 客户端的权限范围，此处配置为all全部即可
*/
        // 从数据库中加载客户端详情
        clients.withClientDetails(jdbcClientDetailsService());
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService() {
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        return jdbcClientDetailsService;
    }

    /**
     * 认证服务器是玩转token的，那么这⾥配置token令牌管理相关（token此时就是⼀个字符串，当下的token需要在服务器端存储，
     *  那么存储在哪⾥呢？都是在这⾥配置）
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        endpoints.tokenStore(tokenStore()) // 指定token的存储⽅法
                .tokenServices(authorizationServerTokenServices()) // token服务的⼀个描述，可以认为是token⽣成细节的描述，⽐如有效时间多少等
                .authenticationManager(authenticationManager) // 指定认证管理器，随后注⼊⼀个到当前类使⽤即可
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    /**
     * 该⽅法⽤于创建tokenStore对象（令牌存储对象）
     *  token以什么形式存储
     */
    public TokenStore tokenStore() {
        // return new InMemoryTokenStore();

        // 使用jwt令牌
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 返回jwt令牌转换器（帮助我们生成jwt令牌的）
     * 在这里，我们可以把签名秘钥传递进去给转换器对象
     * @return
     */
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(sign_key); // 签名秘钥
        jwtAccessTokenConverter.setVerifier(new MacSigner(sign_key)); // 验证时使用的密钥, 和签名密钥保持一致
        jwtAccessTokenConverter.setAccessTokenConverter(lagouAccessTokenConvertor);
        return jwtAccessTokenConverter;
    }

    /**
     * 该⽅法⽤户获取⼀个token服务对象（该对象描述了token有效期等信息）
     */
    public AuthorizationServerTokenServices authorizationServerTokenServices() {
        // 使用默认实现
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setSupportRefreshToken(true); // 是否开启令牌刷新
        defaultTokenServices.setTokenStore(tokenStore());

        // 针对jwt令牌的添加
        defaultTokenServices.setTokenEnhancer(jwtAccessTokenConverter());

        // 设置令牌有效时间（⼀般设置为2个⼩时）
        defaultTokenServices.setAccessTokenValiditySeconds(30); // access_token就是我们请求资源需要携带的令牌
        defaultTokenServices.setRefreshTokenValiditySeconds(259200); // 3天
        return defaultTokenServices;
    }
}

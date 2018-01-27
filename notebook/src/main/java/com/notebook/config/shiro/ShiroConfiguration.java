package com.notebook.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;
  



import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
  
/**
 * Shiro 配置
 *
 *	Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 *	既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 *
 */
@Configuration
public class ShiroConfiguration {
       
    
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
       //System.out.println("ShiroConfiguration.shirFilter()=====>已启动Shiro拦截器");
       ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
       
        // 必须设置 SecurityManager 
       shiroFilterFactoryBean.setSecurityManager(securityManager);
       
       //拦截器.
       Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
       
       //filterChainDefinitionMap.put("/**", "anon"); //测试用打开所有权限
       
       //静态资源
       filterChainDefinitionMap.put("/static/**", "anon");
       
       //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
       filterChainDefinitionMap.put("/logout", "logout");

       //配置记住我或认证通过可以访问的地址
       //filterChainDefinitionMap.put("/ui/index", "user");
       filterChainDefinitionMap.put("/ui/**", "user");
       filterChainDefinitionMap.put("/admin/**", "user");
       //filterChainDefinitionMap.put("/commons/**", "user");
       
       //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
       //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
       
       //从数据库动态设置权限管理
       //不使用
       //filterChainDefinitionMap.put("/ui/userDel", "roles[admin],perms[userDelete]");
       
       //所有 用户/管理员 方法都拦截
       filterChainDefinitionMap.put("/ui/**", "authc");
       filterChainDefinitionMap.put("/admin/**", "authc");
       filterChainDefinitionMap.put("/commons/**", "authc");
       
       //不拦截登陆方法
       filterChainDefinitionMap.put("/sign", "anon");
       
       
       // 如果不设置默认会自动寻找Web工程根目录下的"/login" 页面(文件)
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/ui/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
       
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        
        return shiroFilterFactoryBean;
    }
    
    /**
     * 
     * @author 2ing
     * @createTime 2018年1月12日
     * @remarks 获取自定义身份认证realm的方法
     */
    @Bean
    public NotebookShiroRelam notebookShiroRealm(){
    	NotebookShiroRelam notebookShiroRealm = new NotebookShiroRelam();
    	notebookShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());;//在自定义relam中设置加密器
       return notebookShiroRealm;
    }
    
    /**
     * 
     * @author 2ing
     * @createTime 2018年1月12日
     * @remarks 获取securityManager的方法
     */
    @Bean
    public SecurityManager securityManager(){
       DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
       //加入字节自己设置的relam
       securityManager.setRealm(notebookShiroRealm());
       //加入缓存管理器
       securityManager.setCacheManager(ehCacheManager());
       //加入rememberme管理器
       securityManager.setRememberMeManager(rememberMeManager());
       return securityManager;
    }
    
    /**
     * 
     * @author 2ing
     * @createTime 2018年1月13日
     * @remarks 凭证匹配器/加密器，使用shiro进行加密验证，需要我们自己设置加密相关设置
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
    	HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
    	hashedCredentialsMatcher.setHashAlgorithmName("MD5");//散列算法：md5
    	hashedCredentialsMatcher.setHashIterations(32);//散列次数:32
    	return hashedCredentialsMatcher;
    }
    
    
    /**
     * Shiro生命周期处理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    /**
     * 
     * @author 2ing
     * @createTime 2018年1月14日
     * @remarks 启动shiro的@requireXXX注释
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    
    /**  
     *  开启shiro aop注解支持.  
     *  使用代理方式;所以需要开启代码支持;  
     * @param securityManager  
     * @return  
     */  
    @Bean  
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager){  
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }  
    

    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 	安全管理器：securityManager
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager(){
       EhCacheManager cacheManager = new EhCacheManager();
       cacheManager.setCacheManagerConfigFile("classpath:config/shiro/ehcache-shiro.xml");
       return cacheManager;
    }
    
    /**
     * rememberMe使用
     * cookie对象
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
       //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
       SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
       //<!-- 记住我cookie生效时间30天 ,单位秒;-->
       simpleCookie.setMaxAge(259200);
       return simpleCookie;
    }
    
    /**
     * rememberMe使用
     * cookie管理对象
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
       CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
       cookieRememberMeManager.setCookie(rememberMeCookie());
       
       //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
       cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
       return cookieRememberMeManager;
    }
        
}
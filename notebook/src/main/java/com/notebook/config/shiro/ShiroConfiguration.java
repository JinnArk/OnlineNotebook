package com.notebook.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;
  



import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
  
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
       System.out.println("ShiroConfiguration.shirFilter()=====>已启动Shiro拦截器");
       ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
       
        // 必须设置 SecurityManager 
       shiroFilterFactoryBean.setSecurityManager(securityManager);
       
       //拦截器.
       Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
       
       //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
       filterChainDefinitionMap.put("/logout", "logout");
       
       //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
       
       //所有 用户/管理员 方法都拦截
       filterChainDefinitionMap.put("/ui/**", "authc");
       filterChainDefinitionMap.put("/admin/**", "authc");
       
       //不拦截登陆方法
       filterChainDefinitionMap.put("/sign", "anon");
       
       // 如果不设置默认会自动寻找Web工程根目录下的"/login" 页面(文件)
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/ui/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
       
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
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
       AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
       authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
       return authorizationAttributeSourceAdvisor;
    }
        
}
//package com.bss.iqs.config;
//
//
//import com.bss.iqs.shiro.MyShiroRealm;
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.cache.ehcache.EhCacheManager;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.apache.shiro.mgt.SecurityManager;
//import org.springframework.web.filter.DelegatingFilterProxy;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Configuration
//public class ShiroConfiguration {
//
//
//    /**
//     * ShiroFilterFactoryBean 处理拦截资源文件问题。
//     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
//     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
//     *
//     Filter Chain定义说明
//     1、一个URL可以配置多个Filter，使用逗号分隔
//     2、当设置多个过滤器时，全部验证通过，才视为通过
//     3、部分过滤器可指定参数，如perms，roles
//     *
//     */
//    @Bean
//    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
//
//        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
//        // 必须设置 SecurityManager
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        //拦截器.
//        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
//        //如果不配置，默认跳到/login.jsp
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        // 登录成功后要跳转的链接，不生效
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//        //未授权界面;
//       // shiroFilterFactoryBean.setUnauthorizedUrl("/user/success");
//       // filterChainDefinitionMap.put("/logout", "logout");
//        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/favicon.ico", "anon");
//        filterChainDefinitionMap.put("/**", "authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
//
//
//    @Bean
//    public SecurityManager securityManager(){
//        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
//        securityManager.setRealm(myShiroRealm());
//   //     securityManager.setCacheManager(ehCacheManager());
//        return securityManager;
//
//    }
//
//    @Bean
//    public MyShiroRealm myShiroRealm(){
//        MyShiroRealm myShiroRealm = new MyShiroRealm();
//        //myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//
//        return myShiroRealm;
//    }
//
////    @Bean
////    public EhCacheManager ehCacheManager() {
////        EhCacheManager em = new EhCacheManager();
////        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
////        return em;
////    }
//
////    @Bean
////    public HashedCredentialsMatcher hashedCredentialsMatcher(){
////        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
////
////        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
////        hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列两次，相当于 md5(md5(""));
////
////        return hashedCredentialsMatcher;
////    }
//
//    /**
//     *  开启shiro aop注解支持.
//     *  使用代理方式;所以需要开启代码支持;
//     * @param securityManager
//     * @return
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//
//    @Bean(name = "lifecycleBeanPostProcessor")
//    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean
//    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
//        daap.setProxyTargetClass(true);
//        return daap;
//    }
//
//
//}

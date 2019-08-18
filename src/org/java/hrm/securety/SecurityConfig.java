package org.java.hrm.securety;

import org.java.hrm.util.common.HrmConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 自动注入applicationContext中声明的c3p0数据源
    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用内存中存储的用户信息
        /*auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");*/

        // 使用数据库表中的用户信息
        String roleSqlStr = "SELECT u.username, sr.roleName  FROM `security_user_role` as sur, `user_inf` as u, `security_role` as sr WHERE u.id = sur.userid AND sr.id = sur.roleid AND u.username = ?";


        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select username, password, true from " + HrmConstants.USERTABLE + " where username=?")
            .authoritiesByUsernameQuery(roleSqlStr);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().formLogin() // 使用表单进行身份验证
            .loginPage("/hello") // 自定义登录页面
            .loginProcessingUrl("/loginSecurity") // 自定义登录路径
            .failureUrl("/500")
            .defaultSuccessUrl("/main")
            .and()
            .rememberMe() // 开启rememberMe功能
            .tokenValiditySeconds(2419200)
            .key("hrmKey")
            .and()
            .authorizeRequests() // 开始进行安全性配置
            .antMatchers("/hello", "/static/**").permitAll() // 放开对/hello和静态资源的限制
            .antMatchers("/dept/removeDept", "/user/removeUser", "/role/removeRole", "/employee/removeEmployee").access("hasRole('ROLE_ROOT')")
            .antMatchers("/dept/addDept", "/user/addUser", "/role/addRole", "/employee/addEmployee").access("hasAnyRole('ROLE_ROOT', 'ROLE_MANAGE')")
            .antMatchers("/dept/updateDept", "/user/updateUser", "/role/updateRole", "/employee/updateEmployee").access("hasAnyRole('ROLE_ROOT', 'ROLE_MANAGE')")
            .anyRequest().authenticated(); // 除去/hello请求外，其余的请求必须登录才可以
    }
}

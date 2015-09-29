package io.honeymon.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final int PASSWORD_ENCODER_STRENTH = 11;

    /**
     * 개발초기에는 스프링시큐리티 적용이 되지 않도록 한다.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll().and().httpBasic().and().csrf();
    }

    /**
     * 애플리케이션의 정적자원에 대한 보안적용을 무시
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**");
    }

    /**
     * 애플리케이션의 암호화를 담당
     * 
     * @return {@link BCryptPasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(PASSWORD_ENCODER_STRENTH);
    }

    /**
     * 사용자이름대신, 사용자이름을 암호화한 값을 salt값으로 사용하겠다.
     * 
     * @return {@link ReflectionSaltSource}
     */
    @Bean
    public SaltSource saltSource() {
        ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("saltKey");
        return saltSource;
    }
}

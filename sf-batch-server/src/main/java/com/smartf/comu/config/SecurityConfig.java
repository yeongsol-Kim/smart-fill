package com.smartf.comu.config;

import com.smartf.comu.handler.AuthFailureHandler;
import com.smartf.comu.handler.AuthSuccessHandler;
import com.smartf.comu.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailureHandler authFailureHandler;

    public SecurityConfig(AuthSuccessHandler authSuccessHandler, AuthFailureHandler authFailureHandler) {
        this.authSuccessHandler = authSuccessHandler;
        this.authFailureHandler = authFailureHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().headers().frameOptions().disable() //csrf토큰 비활성화
            .and()
                .authorizeRequests()
                    .antMatchers("/login", "/register", "/signup", "/js/**", "/css/**", "/vender/**", "/img/**").permitAll()
                    //.anyRequest().authenticated()
                    .anyRequest().hasRole("ADMIN")
            .and()
                .formLogin()
                    .loginPage("/login")
                    //.usernameParameter("email")
                    .defaultSuccessUrl("/")
                    .successHandler(authSuccessHandler)
                    .failureHandler(authFailureHandler)
            .and()
                .logout()
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true) // 인증정보 삭제, 세션 무효화
                    //.deleteCookies("JSESSIONID", "remember-me")
                    .permitAll()
            .and()
                .sessionManagement()
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false) // true=중복 로그인 금지, flase=이전 로그인 세션 해제
                    .expiredUrl("/login")// 세션 만료시 이동
                .and()
//            .and().rememberMe()
//                .alwaysRemember(false)
//                .tokenValiditySeconds(3600)
//                .rememberMeParameter("remember-me");
        ;

    }
}


package com.smartf.comu.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String msg = "이메일 또는 패스워드가 잘못되었습니다.";

        if (exception instanceof DisabledException) {
            msg = "계정이 비활성화 상태입니다.";
        } else if(exception instanceof CredentialsExpiredException){
            msg = "비밀번호가 만료되었습니다.";
        } else if(exception instanceof BadCredentialsException) {
            msg = "비밀번호가 일치하지 않습니다.";
        } else if(exception instanceof UsernameNotFoundException) {
            msg = "이메일이 잘못되었습니다.";
        }

        setDefaultFailureUrl("/login?error=true&exception=" + msg);

        super.onAuthenticationFailure(request, response, exception);

    }

}

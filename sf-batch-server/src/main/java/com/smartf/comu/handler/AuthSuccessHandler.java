package com.smartf.comu.handler;

import com.smartf.comu.repository.SpringDataJpaMemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final SpringDataJpaMemberRepository memberRepository;

    public AuthSuccessHandler(SpringDataJpaMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        setDefaultTargetUrl("/");

        super.onAuthenticationSuccess(request, response, authentication);
    }
}

package com.jeorigagye.config.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jeorigagye.config.security.auth.PrincipalDetail;
import com.jeorigagye.domain.Member;
import com.jeorigagye.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepsitory memberRepsitory;

    public AuthorizationFilter(AuthenticationManager authenticationManager, MemberRepsitory memberRepsitory) {
        super(authenticationManager);
        this.memberRepsitory = memberRepsitory;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("인증이나 권한이 필요한 주소 요청이 됨.");

        String jwtHeader = request.getHeader("Authorization");

        if(jwtHeader == null || jwtHeader.startsWith("Bearer")){
            chain.doFilter(request, response);
            return;
        }

        String token = jwtHeader.replace("Bearer ", "").trim();
        String membername = JWT.require(Algorithm.HMAC512("cos")).build().verify(token).getClaim("membername").asString();

        if(membername != null && !membername.equals("")){
            Member findMember = memberRepsitory.findByMembername(membername)
                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

            PrincipalDetail principalDetails = new PrincipalDetail(findMember);

            // JWT 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}

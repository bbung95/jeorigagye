package com.jeorigagye.config.security.auth;

import com.jeorigagye.domain.Member;
import com.jeorigagye.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private MemberRepsitory memberRepsitory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("PrincipalDetailService = " + username);
        
        Member findMember = memberRepsitory.findByMembername(username);
        PrincipalDetail principalDetail = PrincipalDetail.builder()
                .member(findMember)
                .build();

        return principalDetail;
    }
}

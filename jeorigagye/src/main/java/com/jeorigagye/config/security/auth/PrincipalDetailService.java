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

    private final MemberRepsitory memberRepsitory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member findMember = memberRepsitory.findByMembername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return new PrincipalDetail(findMember);
    }
}

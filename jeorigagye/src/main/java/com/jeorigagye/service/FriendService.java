package com.jeorigagye.service;

import com.jeorigagye.domain.Friend;
import com.jeorigagye.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    public void friendDuplicatedCheck(Long memberId, Long targetId){

        Friend friend = friendRepository.findFriendByMemberIdAndTargetId(memberId, targetId);

        if(friend != null){
            throw new IllegalStateException("이미 친구추가가 되있습니다.");
        }
    }
}

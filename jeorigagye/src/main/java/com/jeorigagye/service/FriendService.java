package com.jeorigagye.service;

import com.jeorigagye.domain.Friend;
import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.Search;
import com.jeorigagye.dto.member.MemberDto;
import com.jeorigagye.repository.FriendRepository;
import com.jeorigagye.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final MemberRepsitory memberRepsitory;

    public ResponseEntity<List<MemberDto>> findFriendList(Search search){

        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Member> findMembers = friendRepository.findFriendByMemberId(search.getMemberId(), pageRequest);

        List<MemberDto> memberList = findMembers.getContent()
                .stream()
                .map(Member::toMemberDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    public ResponseEntity<Long> registFriend(Long memberId, Long targetId){

        Member findMember = memberRepsitory.findById(memberId).get();
        Member findTarget = memberRepsitory.findById(targetId).get();

        Friend friend = Friend.buliderFriend(findMember, findTarget);
        Friend saveFriend = friendRepository.save(friend);

        return new ResponseEntity<>(saveFriend.getId(), HttpStatus.OK);
    }

    public ResponseEntity deleteFriend(Long memberId, Long targetId){

        Friend findFriend = friendRepository.findFriendByMemberIdAndTargetId(memberId, targetId);
        friendRepository.delete(findFriend);

        return new ResponseEntity(HttpStatus.OK);
    }

    public void friendDuplicatedCheck(Long memberId, Long targetId){

        Friend friend = friendRepository.findFriendByMemberIdAndTargetId(memberId, targetId);

        if(friend != null){
            throw new IllegalStateException("이미 친구추가가 되있습니다.");
        }
    }
}

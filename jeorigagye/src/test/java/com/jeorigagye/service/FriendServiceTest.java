package com.jeorigagye.service;

import com.jeorigagye.domain.Friend;
import com.jeorigagye.domain.Member;
import com.jeorigagye.repository.FriendRepository;
import com.jeorigagye.repository.MemberRepsitory;
import jdk.internal.org.objectweb.asm.Opcodes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class FriendServiceTest {

    @Autowired
    private FriendService friendService;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private MemberRepsitory memberRepsitory;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("친구추가_TEST")
    @Rollback(value = false)
    public void friendSaveTest() throws Exception {
        //given
        Member member = getMember("bbung95");
        Member member2 = getMember("bbung96");
        memberRepsitory.save(member);
        memberRepsitory.save(member2);

        //when
        Friend friend = getFriend(member, member2);
        friendRepository.save(friend);

        //then
        List<Member> findFriends = friendRepository.findFriendByMemberId(member.getId());

        assertThat(findFriends.size()).isEqualTo(1);
        assertThat(findFriends.get(0).getMembername()).isEqualTo(member2.getMembername());
    }
    
    @Test
    @DisplayName("친구추가_중복_TEST")
    public void friendDuplicatedTest() throws Exception {
        //given
        Member member = getMember("bbung95");
        Member member2 = getMember("bbung96");
        memberRepsitory.save(member);
        memberRepsitory.save(member2);

        //when
        Friend friend = getFriend(member, member2);
        friendRepository.save(friend);

        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> {
            friendService.friendDuplicatedCheck(member.getId(), member2.getId());
        });

        //assertThatThrownBy(() -> friendService.friendDuplicatedCheck(member.getId(), member2.getId())).isInstanceOf(IllegalStateException.class);

        //then
        Assertions.assertEquals(e.getMessage(), "이미 친구추가가 되있습니다.");
    }
    
    @Test
    @DisplayName("친구삭제_TEST")
    public void friendDelteTest() throws Exception {
        //given
        Member member = getMember("bbung95");
        Member member2 = getMember("bbung96");
        memberRepsitory.save(member);
        memberRepsitory.save(member2);

        Friend friend = getFriend(member, member2);
        friendRepository.save(friend);

        //when
        Friend findFriend = friendRepository.findFriendByMemberIdAndTargetId(member.getId(), member2.getId());
        friendRepository.delete(findFriend);
        em.flush();

        //then
        List<Member> findFriends = friendRepository.findFriendByMemberId(member.getId());
        assertThat(findFriends.size()).isEqualTo(0);
    }

    public Member getMember(String membername){

        Member member = Member.builder()
                .membername(membername)
                .password("1234")
                .name("뻥뻥이")
                .build();

        return member;
    }

    public Friend getFriend(Member member, Member target){
        Friend friend = Friend.builder()
                .member(member)
                .target(target)
                .build();
        return friend;
    }
}

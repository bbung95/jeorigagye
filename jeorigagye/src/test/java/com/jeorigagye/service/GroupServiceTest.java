package com.jeorigagye.service;

import com.jeorigagye.domain.Friend;
import com.jeorigagye.domain.Group;
import com.jeorigagye.domain.GroupFriend;
import com.jeorigagye.domain.Member;
import com.jeorigagye.repository.FriendRepository;
import com.jeorigagye.repository.GroupFriendRepository;
import com.jeorigagye.repository.GroupRepository;
import com.jeorigagye.repository.MemberRepsitory;
import org.assertj.core.api.Assertions;
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
class GroupServiceTest {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupFriendRepository groupFriendRepository;
    @Autowired
    private MemberRepsitory memberRepsitory;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("그룹추가_TEST")
    public void groupSaveTest() throws Exception {
        //given
        Member member = getMember("bbung95");
        em.persist(member);
        Group group = getGroup(member);

        //when
        groupRepository.save(group);
        em.flush();
        em.clear();

        //then
        Group findGroup = groupRepository.findById(group.getId()).get();
        assertThat(findGroup.getName()).isEqualTo(group.getName());
    }

    @Test
    @DisplayName("그룹친구추가_TEST")
    @Rollback(value = false)
    public void groupFriendSaveTest() throws Exception {
        //given
        Member member = getMember("bbung95");
        Member member2 = getMember("bbung96");
        memberRepsitory.save(member);
        memberRepsitory.save(member2);

        Friend friend = getFriend(member, member2);
        friendRepository.save(friend);

        Group group = getGroup(member);
        groupRepository.save(group);

        //when
        GroupFriend groupFriend = GroupFriend.builder()
                .group(group)
                .friend(friend)
                .build();
        groupFriendRepository.save(groupFriend);
        em.flush();
        em.clear();

        //then
       Group findGroup = groupRepository.findById(group.getId()).get();
       String findFriendMembername = findGroup.getGroupFriends().get(0).getFriend().getTarget().getMembername();
       assertThat(findFriendMembername).isEqualTo(member2.getMembername());
    }
    
    @Test
    @DisplayName("그룹친구삭제_TEST")
    public void groupFriendremoveTest() throws Exception {
        //given
        Member member = getMember("bbung95");
        Member member2 = getMember("bbung96");
        Member member3 = getMember("bbung97");
        memberRepsitory.save(member);
        memberRepsitory.save(member2);
        memberRepsitory.save(member3);

        Friend friend1 = getFriend(member, member2);
        Friend friend2 = getFriend(member, member3);
        friendRepository.save(friend1);
        friendRepository.save(friend2);

        Group group = getGroup(member);
        groupRepository.save(group);

        GroupFriend groupFriend1 = GroupFriend.builder()
                .group(group)
                .friend(friend1)
                .build();
        GroupFriend groupFriend2 = GroupFriend.builder()
                .group(group)
                .friend(friend2)
                .build();
        groupFriendRepository.save(groupFriend1);
        groupFriendRepository.save(groupFriend2);

        //when
        groupFriendRepository.delete(groupFriend1);
        em.flush();
        em.clear();

        //then
        Group findGroup = groupRepository.findById(group.getId()).get();

        Assertions.assertThat(findGroup.getGroupFriends().size()).isEqualTo(1);
    
    }
    
    @Test
    @DisplayName("그룹삭제_TEST")
    public void groupRemoveTest() throws Exception {
        //given
        Member member = getMember("bbung95");
        em.persist(member);
        Group group1 = getGroup(member);
        Group group2 = getGroup(member);

        groupRepository.save(group1);
        groupRepository.save(group2);

        //when
        groupRepository.delete(group1);
        em.flush();
        em.clear();

        //then
        List<Group> groups = groupRepository.findAll();

        assertThat(groups.size()).isEqualTo(1);
    }

    public Group getGroup(Member member){
        Group group = Group.builder()
                .name("개발자동아리")
                .member(member)
                .build();

        return group;
    }

    public Friend getFriend(Member member, Member target){
        Friend friend = Friend.builder()
                .member(member)
                .target(target)
                .build();
        return friend;
    }

    public Member getMember(String membername){

        Member member = Member.builder()
                .membername(membername)
                .password("1234")
                .name("뻥뻥이")
                .build();

        return member;
    }
}

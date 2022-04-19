package com.jeorigagye.service;

import com.jeorigagye.domain.Friend;
import com.jeorigagye.domain.Group;
import com.jeorigagye.domain.GroupFriend;
import com.jeorigagye.domain.Member;
import com.jeorigagye.repository.FriendRepository;
import com.jeorigagye.repository.GroupFriendRepository;
import com.jeorigagye.repository.GroupRepository;
import com.jeorigagye.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final MemberRepsitory memberRepsitory;
    private final FriendRepository friendRepository;
    private final GroupFriendRepository groupFriendRepository;

    public ResponseEntity<Long> addGroup(Long memberId, String groupName){

        Member findMember = memberRepsitory.findById(memberId).get();
        Group group = Group.builderGroup(groupName, findMember);

        Group saveGroup = groupRepository.save(group);

        return new ResponseEntity(saveGroup.getId(), HttpStatus.OK);
    }

    public ResponseEntity<Long> registGroupFriend(Long groupId, Long friendId){

        Group findGroup = groupRepository.findById(groupId).get();
        Friend findFriend = friendRepository.findById(friendId).get();

        GroupFriend groupFriend = GroupFriend.builderGroupFriend(findGroup, findFriend);
        GroupFriend saveGroupFriend = groupFriendRepository.save(groupFriend);

        return new ResponseEntity(saveGroupFriend.getId(), HttpStatus.OK);
    }

    public ResponseEntity deleteGroupFriend(Long groupFriendId){

        GroupFriend findGroupFriend = groupFriendRepository.findById(groupFriendId).get();
        groupFriendRepository.delete(findGroupFriend);

        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity deleteGroup(Long groupId){

        Group findGroup = groupRepository.findById(groupId).get();
        groupRepository.delete(findGroup);

        return new ResponseEntity(HttpStatus.OK);
    }

}

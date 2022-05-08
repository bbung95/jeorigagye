package com.jeorigagye.service;

import com.jeorigagye.domain.*;
import com.jeorigagye.dto.Search;
import com.jeorigagye.dto.account.AccountDto;
import com.jeorigagye.dto.group.GroupDto;
import com.jeorigagye.dto.groupFriend.GroupFriendDto;
import com.jeorigagye.enums.AccountType;
import com.jeorigagye.repository.FriendRepository;
import com.jeorigagye.repository.GroupFriendRepository;
import com.jeorigagye.repository.GroupRepository;
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
public class GroupService {

    private final GroupRepository groupRepository;
    private final MemberRepsitory memberRepsitory;
    private final FriendRepository friendRepository;
    private final GroupFriendRepository groupFriendRepository;

    public ResponseEntity<List<GroupDto>> findAll(Search search){

        PageRequest pageRequest = PageRequest.of(search.getCulPage(), 10);

        Page<Group> findGroups = groupRepository.findGroupBySaerch(search.getMemberId(), pageRequest);

        List<GroupDto> groupList = findGroups.getContent()
                .stream()
                .map(Group::toGroupDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(groupList, HttpStatus.OK);
    }

    public ResponseEntity<List<GroupFriendDto>> findFriendAll(Long groupId, Search search){

        PageRequest pageRequest = PageRequest.of(search.getCulPage(), 10);

        Page<GroupFriend> findGroupFriends = groupFriendRepository.findGroupFriendBySearch(groupId, pageRequest);

        List<GroupFriendDto> groupFriendList = findGroupFriends.getContent()
                .stream()
                .map(GroupFriend::toGroupFriendDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(groupFriendList, HttpStatus.OK);
    }

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

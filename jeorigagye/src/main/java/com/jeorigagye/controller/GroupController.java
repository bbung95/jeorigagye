package com.jeorigagye.controller;

import com.jeorigagye.config.security.auth.PrincipalDetail;
import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.Search;
import com.jeorigagye.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group/")
public class GroupController {

    private final GroupService groupService;

    @PostMapping("regist")
    public ResponseEntity groupRegist(@AuthenticationPrincipal PrincipalDetail principalDetail, @RequestParam String groupName){

        Member member = (Member)principalDetail.getMember();

        return groupService.addGroup(member.getId(), groupName);
    }

    @GetMapping
    public ResponseEntity groupList(@AuthenticationPrincipal PrincipalDetail principalDetail, @RequestBody Search search){

        Member member = (Member)principalDetail.getMember();
        search.setMemberId(member.getId());

        return groupService.findAll(search);
    }

    @GetMapping("friend/{groupId}")
    public ResponseEntity groupFriendList(@PathVariable Long groupId, @RequestBody Search search){

        return groupService.findFriendAll(groupId, search);
    }

    @PostMapping("friend")
    public ResponseEntity groupFriendRegist(@RequestParam Long groupId, @RequestParam Long friendId){

        return groupService.registGroupFriend(groupId, friendId);
    }

    @DeleteMapping("{groupId}")
    public ResponseEntity groupDelete(@PathVariable Long groupId){

        return groupService.deleteGroup(groupId);
    }

    @DeleteMapping("{groupFriendId}")
    public ResponseEntity groupFriendDelete(@PathVariable Long groupFriendId){

        return groupService.deleteGroupFriend(groupFriendId);
    }
}
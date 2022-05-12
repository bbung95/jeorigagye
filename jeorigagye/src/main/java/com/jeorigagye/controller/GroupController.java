package com.jeorigagye.controller;

import com.jeorigagye.dto.Search;
import com.jeorigagye.service.GroupService;
import com.jeorigagye.util.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group/")
public class GroupController {

    private final GroupService groupService;

    @PostMapping("regist")
    public ResponseEntity groupRegist(HttpServletRequest request, @RequestParam String groupName){

        Long memberId = AuthToken.tokenParse(request);

        return groupService.addGroup(memberId, groupName);
    }

    @GetMapping
    public ResponseEntity groupList(HttpServletRequest request, @RequestBody Search search){

        Long memberId = AuthToken.tokenParse(request);
        search.setMemberId(memberId);

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

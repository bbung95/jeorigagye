package com.jeorigagye.controller;

import com.jeorigagye.config.security.auth.PrincipalDetail;
import com.jeorigagye.domain.Member;
import com.jeorigagye.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend/")
public class FriendController {

    private final FriendService friendService;

    @PostMapping("regist")
    public ResponseEntity friendRegist(@AuthenticationPrincipal PrincipalDetail principalDetail, @RequestParam Long targetId){

        Member member = (Member)principalDetail.getMember();

        return friendService.registFriend(member.getId(), targetId);
    }

    @GetMapping
    public ResponseEntity friendList(@AuthenticationPrincipal PrincipalDetail principalDetail){

        Member member = (Member)principalDetail.getMember();

        return friendService.findFriendList(member.getId());
    }

    @DeleteMapping("{targetId}")
    public ResponseEntity friendDelete(@AuthenticationPrincipal PrincipalDetail principalDetail, @PathVariable Long targetId){

        Member member = (Member)principalDetail.getMember();

        return friendService.deleteFriend(member.getId(), targetId);
    }
}

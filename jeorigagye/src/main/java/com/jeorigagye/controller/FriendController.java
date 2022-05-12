package com.jeorigagye.controller;

import com.jeorigagye.dto.Search;
import com.jeorigagye.service.FriendService;
import com.jeorigagye.util.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend/")
public class FriendController {

    private final FriendService friendService;

    @PostMapping("regist")
    public ResponseEntity friendRegist(HttpServletRequest request, @RequestParam Long targetId){

        Long memberId = AuthToken.tokenParse(request);

        return friendService.registFriend(memberId, targetId);
    }

    @GetMapping
    public ResponseEntity friendList(HttpServletRequest request, @RequestBody Search search){

        Long memberId = AuthToken.tokenParse(request);
        search.setMemberId(memberId);

        return friendService.findFriendList(search);
    }

    @DeleteMapping("{targetId}")
    public ResponseEntity friendDelete(HttpServletRequest request, @PathVariable Long targetId){

        Long memberId = AuthToken.tokenParse(request);

        return friendService.deleteFriend(memberId, targetId);
    }
}

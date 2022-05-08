package com.jeorigagye.dto.groupFriend;

import com.jeorigagye.domain.Friend;
import com.jeorigagye.domain.Group;
import com.jeorigagye.domain.GroupFriend;
import lombok.*;

@Setter
@Getter
@ToString
public class GroupFriendDto {

    private Long id;
    private Group group;
    private Friend friend;

    @Builder
    public GroupFriendDto(Long id, Group group, Friend friend){
        this.id = id;
        this.group = group;
        this.friend = friend;
    }
}

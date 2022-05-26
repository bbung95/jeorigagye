package com.jeorigagye.dto.group;

import com.jeorigagye.domain.GroupFriend;
import com.jeorigagye.domain.Member;
import com.jeorigagye.domain.extend.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "groups")
public class GroupDto extends BaseTimeEntity {

    private Long id;

    private String name;

    private List<GroupFriend> groupFriends = new ArrayList<>();

    private Member member;

    @Builder
    public GroupDto(String name, Member member) {
        this.name = name;
        this.member = member;
    }

    public static GroupDto builderGroup(String groupName, Member member){
        return GroupDto.builder()
                .name(groupName)
                .member(member)
                .build();
    }
}

package com.jeorigagye.domain;

import com.jeorigagye.domain.extend.BaseTimeEntity;
import com.jeorigagye.dto.groupFriend.GroupFriendDto;
import com.jeorigagye.repository.GroupFriendRepository;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "group_friend")
public class GroupFriend extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private Friend friend;

    @Builder
    public GroupFriend(Group group, Friend friend){
        this.group = group;
        this.friend = friend;
    }

    public static GroupFriend builderGroupFriend(Group group, Friend friend){
        return GroupFriend.builder()
                .group(group)
                .friend(friend)
                .build();
    }

    public GroupFriendDto toGroupFriendDto(){
        return GroupFriendDto.builder()
                .id(this.id)
                .friend(this.friend)
                .group(this.group)
                .build();
    }
}

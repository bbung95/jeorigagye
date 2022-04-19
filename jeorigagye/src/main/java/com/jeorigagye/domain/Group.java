package com.jeorigagye.domain;

import com.jeorigagye.domain.extend.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "groups")
public class Group extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "group" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GroupFriend> groupFriends = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Group(String name, Member member) {
        this.name = name;
        this.member = member;
    }

    public static Group builderGroup(String groupName, Member member){
        return Group.builder()
                .name(groupName)
                .member(member)
                .build();
    }
}

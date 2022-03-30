package com.jeorigagye.domain;

import com.jeorigagye.domain.extend.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class GroupFriend extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private Friend friend;
}

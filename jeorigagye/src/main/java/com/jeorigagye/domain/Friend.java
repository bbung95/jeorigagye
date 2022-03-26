package com.jeorigagye.domain;

import com.jeorigagye.domain.extend.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Friend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private Member target;

    public Friend() {
    }


}

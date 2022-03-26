package com.jeorigagye.domain;

import com.jeorigagye.domain.extend.BaseEntity;
import com.jeorigagye.enums.AccountType;

import javax.persistence.*;

@Entity
public class Account extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int price;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Account() {
    }

    public void setMember(Member member){
        this.member = member;
        member.getAccounts().add(this);
    }

}

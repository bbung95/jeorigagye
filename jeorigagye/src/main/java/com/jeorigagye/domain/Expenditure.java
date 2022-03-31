package com.jeorigagye.domain;

import com.jeorigagye.domain.extend.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Expenditure extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String price;

    private String name;

    private LocalDateTime expenditureDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Expenditure() {
    }

    public void setMember(Member member){
        this.member = member;
        member.getExpenditures().add(this);
    }
}
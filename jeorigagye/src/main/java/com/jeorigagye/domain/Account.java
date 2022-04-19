package com.jeorigagye.domain;

import com.jeorigagye.domain.extend.BaseTimeEntity;
import com.jeorigagye.enums.AccountType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Account extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder
    public Account (String name, int price, int type, Member member, Category category){
        this.name = name;
        this.price = price;
        this.category = category;
        setMember(member);

        if(type == 1){
            this.type = AccountType.INCOME;
        }else{
            this.type = AccountType.EXPENDITURE;
        }
    }

    public void setMember(Member member){
        this.member = member;
        member.getAccounts().add(this);
    }

}

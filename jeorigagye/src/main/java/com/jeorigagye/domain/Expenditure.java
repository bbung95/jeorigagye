package com.jeorigagye.domain;

import com.jeorigagye.domain.extend.BaseTimeEntity;
import com.jeorigagye.dto.expenditure.ExpenditureDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Expenditure extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price;

    private String name;

    @CreatedDate
    private LocalDateTime expenditureDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

//    public void changeMember(Member member){
//        this.member = member;
//        member.getExpenditures().add(this);
//    }

    @Builder
    public Expenditure(int price, String name, Member member, Category category){
        this.price = price;
        this.name = name;
        this.member = member;
        this.category = category;
    }

    public ExpenditureDto toExpenditureDto(){

        return ExpenditureDto.builder()
                .expenditure(this)
                .build();
    }
}

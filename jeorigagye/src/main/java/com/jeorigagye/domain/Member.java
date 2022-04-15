package com.jeorigagye.domain;

import com.jeorigagye.domain.extend.BaseTimeEntity;
import com.jeorigagye.dto.member.MemberDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String membername;

    private String password;

    private String name;

    private int salary;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    public List<Account>  accounts = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    public List<Expenditure> expenditures = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    public List<Friend> friends = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    public List<Group> groups = new ArrayList<>();

    @Builder
    public Member(String membername, String password, String name){

       this.membername = membername;
       this.password = password;
       this.name = name;
    }

    public void addFriend(Friend friend){
        this.getFriends().add(friend);
    }

    public MemberDto toMemberDto(){

        return MemberDto.builder()
                .member(this)
                .build();
    }
}

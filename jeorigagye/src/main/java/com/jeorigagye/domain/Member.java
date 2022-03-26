package com.jeorigagye.domain;

import com.jeorigagye.domain.extend.BaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Member() {
    }

}

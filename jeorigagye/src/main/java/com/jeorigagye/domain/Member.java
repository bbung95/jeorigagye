package com.jeorigagye.domain;

import com.jeorigagye.domain.extend.BaseEntity;
import com.jeorigagye.dto.MemberForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder(builderMethodName = "MemberBuilder")
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String membername;

    private String password;

    private String name;

    private int salary;

    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    public List<Account>  accounts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    public List<Expenditure> expenditures = new ArrayList<>();


    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    public List<Friend> friends = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    public List<Group> groups = new ArrayList<>();

    protected Member() {
    }

    public static MemberBuilder builder(MemberForm form){

        return MemberBuilder()
                .membername(form.getMembername())
                .password(form.getPassword())
                .name(form.getName());
    }

}

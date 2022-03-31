package com.jeorigagye.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String name;

    @Column(name = "category_image")
    private String image;

    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY)
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY)
    private List<Expenditure> expenditures = new ArrayList<>();

    public Category() {
    }
}
package org.ssafy.shopping.backend.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 50, nullable = false)
    private int id;

    @Column(length = 100, nullable = false)
    private String password;
    @Column(length = 30, nullable = false)
    private String memberName;

    @Column(length = 100, nullable = false)
    private String memberMail;

    @Column(length = 100, nullable = false)
    private String memberPhone;

    @Column(length = 100, nullable = false)
    private int regDate;

}
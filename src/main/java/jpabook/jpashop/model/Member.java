package jpabook.jpashop.model;


import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    @Embedded
    private Address address;

    @Embedded
    private Name name;

    private String email;

    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();

    private Date birth;

}

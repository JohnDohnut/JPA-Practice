package jpabook.jpashop.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String username;

    @Embedded
    private Address address;

    @Embedded
    private Name name;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();

    private LocalDate birth;

}

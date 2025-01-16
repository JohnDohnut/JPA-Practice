package jpabook.jpashop.model;

import jakarta.persistence.*;

@Entity
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders order;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Embedded
    private Address address;
}

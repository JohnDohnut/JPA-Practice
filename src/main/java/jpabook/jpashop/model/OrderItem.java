package jpabook.jpashop.model;

import jakarta.persistence.*;
import jpabook.jpashop.model.item.Item;

import java.math.BigDecimal;

@Entity
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private BigDecimal orderPrice;

    private Long quantity;


}

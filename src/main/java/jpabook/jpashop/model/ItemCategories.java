package jpabook.jpashop.model;


import jakarta.persistence.*;
import jpabook.jpashop.model.item.Item;

@Entity
public class ItemCategories {
    @Id @GeneratedValue
    @Column(name = "item_categories_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;




}

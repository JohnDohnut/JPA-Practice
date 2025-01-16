package jpabook.jpashop.model.item;

import jakarta.persistence.*;
import jpabook.jpashop.model.ItemCategories;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Getter
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private Long price;
    private Long stockQuantity;
    @Enumerated(value = EnumType.STRING)
    public AgeLimit ageLimit;

    @OneToMany(mappedBy = "item")
    private List<ItemCategories> itemCategories = new ArrayList<>();





}

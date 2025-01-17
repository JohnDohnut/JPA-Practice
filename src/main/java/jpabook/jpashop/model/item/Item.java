package jpabook.jpashop.model.item;

import jakarta.persistence.*;
import jpabook.jpashop.exception.NotEnoughStockException;
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


    public void increaseQuantity(Long value){
        this.stockQuantity += value;
    }

    public void decreaseQuantity(Long value){
        if(this.stockQuantity - value < 0){
            throw new NotEnoughStockException("not enough stock");
        }
        else{
            this.stockQuantity -= value;
        }
    }


}

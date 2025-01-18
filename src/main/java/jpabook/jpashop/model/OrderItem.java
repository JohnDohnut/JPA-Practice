package jpabook.jpashop.model;

import jakarta.persistence.*;
import jpabook.jpashop.model.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    private Long orderPrice;

    private Long quantity;

    public void cancel(){
        this.getItem().increaseQuantity(this.getQuantity());

    }
    public static OrderItem createOrderItem(Item item, Long orderPrice, Long quantity){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setQuantity(quantity);
        try{
            item.decreaseQuantity(quantity);
        }
        catch(Exception e){
            throw e;
        }
        return orderItem;

    }
}

package jpabook.jpashop.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private LocalDateTime orderTime;

    private void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
    }

    private void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public static Orders createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Orders order = new Orders();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(Status.ORDERED);
        order.setOrderTime(LocalDateTime.now());
        return order;

    }

    public void cancelOrder(){
        if(this.status == Status.DELIVERED || this.status == Status.ACCEPTED){
            throw new IllegalStateException("Cannot cancel delivered / accpeted order");
        }
        if(this.status == Status.CANCELLED){
            throw new IllegalStateException("Already Cancelled");

        }

        for(OrderItem orderItem : this.getOrderItems()){
            orderItem.cancel();
        }

        this.status = Status.CANCELLED;
    }

    public Long totalPrice(){
        Long result = 0L;
        for(OrderItem orderItem : orderItems){
            result += orderItem.getOrderPrice();
        }
        return result;
    }



}

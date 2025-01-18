package jpabook.jpashop.service;


import jakarta.transaction.Transactional;
import jpabook.jpashop.model.Delivery;
import jpabook.jpashop.model.Member;
import jpabook.jpashop.model.OrderItem;
import jpabook.jpashop.model.Orders;
import jpabook.jpashop.model.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    public Long order(String memberClaim, Long itemId, Long quantity){
        Member member = memberRepository.findMemberByEmail(memberClaim);
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        Item item = itemRepository.findOne(itemId);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice()*quantity, quantity);

        Orders order = Orders.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();

    }

    public void cancel(Long orderId){

        Orders order = orderRepository.findById(orderId);
        order.cancelOrder();

    }

    public List<Orders> searchAllOrders(String memberClaim){
        return orderRepository.findAllByMember(memberRepository.findMemberByEmail(memberClaim));
    }
}

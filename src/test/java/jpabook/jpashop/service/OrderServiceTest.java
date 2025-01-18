package jpabook.jpashop.service;

import jakarta.transaction.Transactional;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.model.*;
import jpabook.jpashop.model.item.Book;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Test
    public void order() throws Exception{
        Member member = new Member();
        member.setUsername("John");
        member.setAddress(new Address("US", "Ohio", "Jackson", "4235"));
        member.setBirth(LocalDate.of(1982, 10, 25));
        member.setEmail("example-member-1@example.com");
        member.setName(new Name("John", "Doh", ""));
        memberRepository.save(member);

        Book book = new Book();
        book.setName("A Book");
        book.setPrice(10000L);
        book.setStockQuantity(10L);
        itemRepository.save(book);

        Long orderId = orderService.order(member.getEmail(), book.getId(), 3L);
        Orders findOrder = orderRepository.findById(orderId);
        Assertions.assertEquals(Status.ORDERED, findOrder.getStatus());
        Assertions.assertEquals(1, findOrder.getOrderItems().size());
        Assertions.assertEquals(findOrder.getOrderItems().get(0).getQuantity(), 3L);
        Assertions.assertEquals(findOrder.totalPrice(), findOrder.getOrderItems().get(0).getQuantity() * findOrder.getOrderItems().get(0).getItem().getPrice());
        Assertions.assertEquals(book.getStockQuantity(),7L);
    }

    @Test
    public void cancel() throws Exception{
        Member member = new Member();
        member.setUsername("John");
        member.setAddress(new Address("US", "Ohio", "Jackson", "4235"));
        member.setBirth(LocalDate.of(1982, 10, 25));
        member.setEmail("example-member-1@example.com");
        member.setName(new Name("John", "Doh", ""));
        memberRepository.save(member);

        Book book = new Book();
        book.setName("A Book");
        book.setPrice(10000L);
        book.setStockQuantity(10L);
        itemRepository.save(book);

        Long orderId = orderService.order(member.getEmail(), book.getId(), 3L);

        Orders findOrder = orderRepository.findById(orderId);
        findOrder.cancelOrder();

        Assertions.assertEquals(findOrder.getStatus(),Status.CANCELLED);
        Assertions.assertEquals(book.getStockQuantity(), 10L);
    }

    @Test
    public void orderQuantityExceeded() throws Exception{

        Member member = new Member();
        member.setUsername("John");
        member.setAddress(new Address("US", "Ohio", "Jackson", "4235"));
        member.setBirth(LocalDate.of(1982, 10, 25));
        member.setEmail("example-member-1@example.com");
        member.setName(new Name("John", "Doh", ""));
        memberRepository.save(member);

        Book book = new Book();
        book.setName("A Book");
        book.setPrice(10000L);
        book.setStockQuantity(10L);
        itemRepository.save(book);

        Assertions.assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getEmail(),book.getId(), 11L));
    }

}
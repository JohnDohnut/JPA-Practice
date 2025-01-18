package jpabook.jpashop.repository;


import jakarta.persistence.EntityManager;
import jpabook.jpashop.model.Member;
import jpabook.jpashop.model.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public Long save(Orders order){
        em.persist(order);
        return order.getId();
    }

    public Orders findById(Long id){
        return em.find(Orders.class, id);
    }

    public List<Orders> findAllByMember(Member member){
        List<Orders> ordersList = em.createQuery("select o from Orders o where member = :member")
                .setParameter("member", member)
                .getResultList();
        return ordersList.isEmpty() ? null : ordersList;
    }

    public List<Orders> findAll(){
        List<Orders> ordersList = em.createQuery("select o from Orders o", Orders.class).getResultList();
        return ordersList.isEmpty() ? null : ordersList;
    }


}

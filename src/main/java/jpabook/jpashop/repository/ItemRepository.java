package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.model.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    private void save(Item item){
        if(item.getId() == null){
            em.persist(item);
        }
        else{
            em.merge(item);
        }

    }

    public Item findOne(Long itemId){
        return em.find(Item.class, itemId);

    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

    public Item findItemByName(String itemName){
        List<Item> items = em.createQuery("select i from Item i where name = :itemName")
                .setParameter("itemName", itemName)
                .getResultList();
        return items.isEmpty() ? null : items.get(0);
    }
}

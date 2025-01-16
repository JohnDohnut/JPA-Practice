package jpabook.jpashop.model.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("book")
@Getter
public class Book extends Item{
    private String author;
    private String ISBN;
    private String translator;


}

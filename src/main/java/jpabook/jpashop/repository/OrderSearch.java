package jpabook.jpashop.repository;

import jpabook.jpashop.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName;
    private Status orderStatus;
}

package jpabook.jpashop.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Name {
    private String first_name;
    private String last_name;
    private String middle_name;

    protected Name(){}
    public Name(String first_name, String last_name, String middle_name){
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
    }
}

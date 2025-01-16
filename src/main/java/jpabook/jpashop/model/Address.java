package jpabook.jpashop.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String nation;
    private String city;
    private String street_1;
    private String street_2;
    private String zipcode;

    protected Address(){}

    public Address(String city, String street_1, String street_2, String zipcode){
        this.nation = nation;
        this.city = city;
        this.street_1 = street_1;
        this.street_2 = street_2;
        this.zipcode = zipcode;


    }
}

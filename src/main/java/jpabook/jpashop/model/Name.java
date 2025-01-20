package jpabook.jpashop.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Embeddable
@Getter
public class Name {

    @NotEmpty(message = "First name is required.")
    private String first_name;

    @NotEmpty(message = "Last name is required.")
    private String last_name;

    private String middle_name; // Optional field, no validation needed

    protected Name() {}

    public Name(String first_name, String last_name, String middle_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
    }
}

package jpabook.jpashop.controller.member;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jpabook.jpashop.model.Address;
import jpabook.jpashop.model.Name;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "Username is required.")
    private String username;

    @NotEmpty(message = "Address is required.")
    private String nation; // 초기화 추가

    @NotEmpty(message = "Address is required.")
    private String city; // 초기화 추가

    @NotEmpty(message = "Address is required.")
    private String street_1; // 초기화 추가

    @NotEmpty(message = "Address is required.")
    private String street_2; // 초기화 추가

    @NotEmpty(message = "Address is required.")
    private String zipcode; // 초기화 추가

    @NotEmpty(message = "Name is required.")
    private String first_name;

    private String middle_name;

    @NotEmpty(message = "Name is required.")
    private String last_name;

    @NotEmpty(message = "Email is required.")
    private String email;

    @NotNull(message = "Birth date is required.")
    @PastOrPresent(message = "Birth date must be in the past or present.")
    private LocalDate birth;
}
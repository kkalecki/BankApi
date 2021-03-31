package pl.kalecki.BankApi.controller.dto;

import lombok.Data;

import javax.persistence.Column;

//TODO
@Data
public class UserRequest {

    private final  String userName;
    private final  String email;
}

package pl.kalecki.BankApi.controller.dto;

import lombok.Builder;
import lombok.Data;
import pl.kalecki.BankApi.repository.entity.Account;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

//TODO
@Data
@Builder
public class UserResponse {

    private long id;
    private  String userName;
    private  String email;
    private List<Long> accounts;
}

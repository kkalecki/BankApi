package pl.kalecki.BankApi.service.mapper;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import pl.kalecki.BankApi.controller.dto.UserRequest;
import pl.kalecki.BankApi.controller.dto.UserResponse;
import pl.kalecki.BankApi.repository.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse mapUserToUserResponse(User user)
    {
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .accounts(CollectionUtils.emptyIfNull(user.getAccounts()).stream().map(account -> account.getId()).collect(Collectors.toList()))
                .build();

        return userResponse;
    }

    public User mapUserRequestToUser(UserRequest userRequest)
    {
        User user = User.builder()
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .build();
        return user;
    }

    public List<UserResponse> mapUsersToUserResponses(List<User> all)
    {
        List<UserResponse> userResponses = all.stream().map(user -> UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .accounts(user.getAccounts().stream().map(account -> account.getId()).collect(Collectors.toList()))
                .build())
                .collect(Collectors.toList());
        return userResponses;
    }
}

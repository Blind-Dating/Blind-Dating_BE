package com.blind.dating.dto.response;

import com.blind.dating.domain.UserAccount;
import com.blind.dating.dto.MessageDto;
import com.blind.dating.dto.TokenDto;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
public class UserResponse {

    private TokenDto token;
    private String nickname;


    protected UserResponse(TokenDto token, String nickname) {
        this.token = token;
        this.nickname = nickname;
    }

    public static UserResponse of(TokenDto token, String nickname) {
        return new UserResponse(token, nickname);
    }

    public static UserResponse from(UserAccount user, String token){
        return new UserResponse(
                new TokenDto(token),
                user.getNickname()
        );
    }
}

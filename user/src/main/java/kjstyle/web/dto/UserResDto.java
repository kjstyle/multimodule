package kjstyle.web.dto;

import kjstyle.domain.repository.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResDto {
    private Long userNo;
    private String userId;
    private String name;
    private String email;
    private String nickName;
    private String birthYearMonth;

    public static UserResDto of(User user) {
        return UserResDto.builder()
                .userNo(user.getUserNo())
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .birthYearMonth(user.getBirthYearMonth())
                .build();
    }
}
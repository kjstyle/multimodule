package kjstyle.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import kjstyle.domain.repository.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto {

    @NotBlank(message = "아이디는 필수 입니다.")
    private String userId;

    @NotBlank(message = "패스워드는 필수 입니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수입니다.")
    private String passwordCheck;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 주소여야 합니다.")
    private String email;

    private String nickName;

    @Pattern(
            regexp = "^(19\\d{2}|20\\d{2})(0[1-9]|1[0-2])$",
            message = "생년월일은 yyyymm 형식이며, 1900년 이후여야 합니다."
    )
    private String birthYearMonth;

    public User toUser() {
        return User.builder()
                .userId(this.userId)
                .password(this.password)  // 반드시 서비스에서 인코딩 처리!
                .name(this.name)
                .email(this.email)
                .nickName(this.nickName)
                .birthYearMonth(this.birthYearMonth)
                .build();
    }
}

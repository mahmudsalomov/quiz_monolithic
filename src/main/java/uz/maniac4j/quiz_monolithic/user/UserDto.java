package uz.maniac4j.quiz_monolithic.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String phone;
    @NotNull
    @NotBlank
    private String fio;
}

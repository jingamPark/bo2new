package com.example.bo2.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecUserDTO {



    private Long sno;

    @NotEmpty(message = "사용자ID는 필수 항목입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password1; //password1과 2 를 비교하여 유효성검사를 위해서
    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password2;

    private String password;

    @NotEmpty(message = "이메일은 필수 항목입니다.")
    @Email
    private String email;

}

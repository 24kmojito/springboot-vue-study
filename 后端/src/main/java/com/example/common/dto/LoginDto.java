package com.example.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {
    @NotBlank(message = "邮箱不能为空")
    private String user_email;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotNull(message = "身份不能为空")
    private int user_status;    //1表示金融机构，2表示企业，3表示平台管理员
}

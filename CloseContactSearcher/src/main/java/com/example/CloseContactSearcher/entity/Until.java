package com.example.CloseContactSearcher.entity;

import com.example.CloseContactSearcher.jsr303.LoginOperation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("注册实体类")
public class Until implements Serializable {
    @Pattern(
            regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$",
            message = "手机号不符合规范")
    @ApiModelProperty(value = "账号",position = 1)
    private String account;


    @ApiModelProperty(value = "验证码",position = 2)
    private String code;

    @NotBlank(message = "密码不能为空", groups = {LoginOperation.class})
    @ApiModelProperty(value = "密码",position = 3)
    private String password;

    @ApiModelProperty(value = "mac信息",position = 4)
    private String info;
}

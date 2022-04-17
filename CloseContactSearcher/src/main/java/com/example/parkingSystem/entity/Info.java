package com.example.parkingSystem.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.example.parkingSystem.jsr303.LoginOperation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("mac匹配实体类")
public class Info implements Serializable {
    private static final long serialVersionUID = 1528247366805849494L;

    @Min(value = 0,message = "格式错误")
    @ApiModelProperty(value = "mac匹配id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Pattern(
            regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$",
            message = "手机号不符合规范")
    @ApiModelProperty(value = "账号",position = 1)
    private String account;

    @Length(max = 128, message = "mac过长")
    @NotBlank(message = "mac不能为空", groups = {LoginOperation.class})
    @ApiModelProperty(value = "mac",position = 2)
    private String mac;

    @ApiModelProperty(value = "接触时间",hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime time;
}

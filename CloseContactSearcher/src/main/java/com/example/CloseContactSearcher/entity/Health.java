package com.example.CloseContactSearcher.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@ApiModel("健康登记匹配实体类")
public class Health implements Serializable {
    private static final long serialVersionUID = 1528247366805849494L;

    @Min(value = 0,message = "格式错误")
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Pattern(
            regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$",
            message = "手机号不符合规范")
    @ApiModelProperty(value = "账号",position = 1)
    private String account;

    @Length(max = 20, message = "姓名过长")
    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名",position = 2)
    private String username;

    @NotBlank(message = "体温不能为空")
    @ApiModelProperty(value = "体温",position = 3)
    private String temperature;

    @NotBlank(message = "疑似症状不能为空")
    @ApiModelProperty(value = "疑似症状",position = 4)
    private String symptom;

    @NotBlank(message = "地址不能为空")
    @ApiModelProperty(value = "地址",position = 5)
    private String address;

    @NotBlank(message = "健康状况不能为空")
    @ApiModelProperty(value = "健康状况",position = 6)
    private String health;

    @ApiModelProperty(value = "登记时间",hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime time;
}

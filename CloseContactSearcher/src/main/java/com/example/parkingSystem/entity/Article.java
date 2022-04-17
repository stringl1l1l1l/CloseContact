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
@ApiModel("健康登记匹配实体类")
public class Article implements Serializable {
    private static final long serialVersionUID = 1528247366805849494L;

    @Min(value = 0,message = "格式错误")
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "标题",position = 1)
    private String title;

    @NotBlank(message = "摘要不能为空")
    @ApiModelProperty(value = "摘要",position = 2)
    private String summary;

    @NotBlank(message = "日期不能为空")
    @ApiModelProperty(value = "日期",position = 3)
    private String date;

    @NotBlank(message = "浏览量不能为空")
    @ApiModelProperty(value = "浏览量",position = 4)
    private int views;
}

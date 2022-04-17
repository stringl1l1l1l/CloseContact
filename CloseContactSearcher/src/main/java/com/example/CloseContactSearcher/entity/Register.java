package com.example.CloseContactSearcher.entity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("ע��ʵ����")
public class Register implements Serializable {
    @Min(value = 0,message = "��ʽ����")
    @ApiModelProperty(value = "ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Pattern(
            regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$",
            message = "�ֻ��Ų����Ϲ淶")
    @ApiModelProperty(value = "�˺�",position = 1)
    private String account;


    @ApiModelProperty(value = "��֤��",position = 2)
    private String code;

    @ApiModelProperty(value = "�û�ע��ʱ��",hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime time;

}

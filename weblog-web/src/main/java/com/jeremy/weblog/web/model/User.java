package com.jeremy.weblog.web.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@ApiModel(value = "用户实体")
@Data
public class User {
    @ApiModelProperty(value = "用户姓名")
    @NotBlank
    private String username;

    @NotNull
    @ApiModelProperty(value = "用户性别")
    private Integer sex;

    @NotNull
    @Min(18)
    @Max(100)
    @ApiModelProperty(value = "用户年龄")
    private Integer age;

    @NotBlank
    @Email
    @ApiModelProperty(value = "用户邮件")
    private String email;

    private LocalDateTime createTime;
    private LocalDate updateTime;
    private LocalTime time;
}


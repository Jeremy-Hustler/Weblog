package com.jeremy.weblog.web.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class User {
    @NotNull
    private String username;

    @NotNull
    private Integer sex;

    @NotNull
    @Min(18)
    @Max(100)
    private Integer age;

    @NotNull
    @Email
    private String email;
}


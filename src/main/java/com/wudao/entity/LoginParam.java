package com.wudao.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginParam {
    private String username;
    private String password;
}

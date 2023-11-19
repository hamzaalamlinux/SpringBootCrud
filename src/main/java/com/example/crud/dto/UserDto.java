package com.example.crud.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String name;

    private String email;

    private  String password;

    private boolean is_deleted;
}

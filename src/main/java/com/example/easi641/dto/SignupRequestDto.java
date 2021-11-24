package com.example.easi641.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    private String username;
    private String name;
    private String email;
    private int type; // dev rev
    private String password;
    private int nivel;
    private int exp;
    private String rank;
    private String information;
}
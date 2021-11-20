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
    //private String name;
    //private String email;
    //private String token;
    //private int type; // dev rev
    //private LocalDate birthdate;
    private String password;
}
package com.example.easi641.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FollowingDto {
    private String follower;
    private String following;
}

package xyz.feedclap.dtos;

import lombok.Data;

@Data
public class VideogameDto {
    private Long id;
    private String description;
    private String author;
    private Integer rating;
    private String downloadLink;
    private String restriction;
}

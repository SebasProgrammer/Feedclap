package xyz.feedclap.dtos.creates;

import lombok.Data;

@Data
public class CreateVideogameDto {
    private String description;
    private String author;
    private Integer rating;
    private String downloadLink;
    private String restriction;
}
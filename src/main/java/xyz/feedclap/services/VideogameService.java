package xyz.feedclap.services;

import java.util.List;

import xyz.feedclap.dtos.VideogameDto;
import xyz.feedclap.dtos.creates.CreateVideogameDto;
import xyz.feedclap.exceptions.FeedclapException;



public interface VideogameService {
    List<VideogameDto>  getVideogames() throws FeedclapException;
    VideogameDto createVideogame(CreateVideogameDto createVideogameDto) throws FeedclapException;
    VideogameDto getVideogameId(Long videogameId) throws FeedclapException;
}
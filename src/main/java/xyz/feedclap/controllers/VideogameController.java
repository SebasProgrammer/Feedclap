package xyz.feedclap.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import xyz.feedclap.dtos.VideogameDto;
import xyz.feedclap.dtos.creates.CreateVideogameDto;
import xyz.feedclap.exceptions.FeedclapException;
import xyz.feedclap.responses.FeedclapResponse;
import xyz.feedclap.services.VideogameService;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/feedclap"+"/videogames")
public class VideogameController {
	@Autowired
	private VideogameService videogameService;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public FeedclapResponse<VideogameDto> createVideogame(@RequestBody CreateVideogameDto createVideogameDto)
			throws FeedclapException{
		return new FeedclapResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				videogameService.createVideogame(createVideogameDto));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public FeedclapResponse<List<VideogameDto>> getVideogames()
			throws FeedclapException {
		return new FeedclapResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
				videogameService.getVideogames());
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{videogameId}")
	public FeedclapResponse<VideogameDto> getVideogameId(@PathVariable Long videogameId)
			throws FeedclapException {
		return new FeedclapResponse<>( "Success",String.valueOf(HttpStatus.OK), "OK",videogameService.getVideogameId(videogameId));

	}	
}
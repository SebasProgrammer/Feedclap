package xyz.feedclap.theclaps.Common;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xyz.feedclap.theclaps.Dtos.UserDto;
import xyz.feedclap.theclaps.Entities.User;

@Component
public class EntityDtoConverter {

	@Autowired
	private ModelMapper modelMapper;

	public UserDto convertEntityToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	public List<UserDto> convertEntityToDto(List<User> orders) {
		return orders.stream()// Stream<User>
				.map(this::convertEntityToDto)// Stream<UserDto>
				.collect(Collectors.toList());// List<UserDto>
	}

	// public UserDetailDto convertEntityToDtoDetail(UserDetail item) {
	// pub return modelMapper.map(item, UserDetailDto.class);
	// pub}

	// pubpublic List<UserDetailDto> convertEntityToDtoDetail(List<UserDetail>
	// items) {
	// pub return items.stream()// Stream<User>
	// pub .map(this::convertEntityToDtoDetail)// Stream<UserDto>
	// pub .collect(Collectors.toList());// List<UserDto>
	// pub}

}

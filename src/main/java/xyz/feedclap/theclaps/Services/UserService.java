package xyz.feedclap.theclaps.Services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xyz.feedclap.theclaps.Dtos.UserDto;
import xyz.feedclap.theclaps.Entities.User;
import xyz.feedclap.theclaps.Repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public User createUser(UserDto userDto) {
		User newUser = initUser(userDto);
		return userRepository.save(newUser);
	}

	private User initUser(UserDto userDto) {
		User user = new User();
		user.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
		user.setUsername(userDto.getUsername());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setToken(userDto.getToken());
		user.setBirthdate(userDto.getBirthdate());
		user.setType(0);
		return user;
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public Optional<User> findUser(Long id) {
		return userRepository.findById(id);
	}
}

package xyz.feedclap.theclaps.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.feedclap.theclaps.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

package com.example.easi641.repository;

import com.example.easi641.entities.Following;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowingRepository extends JpaRepository<Following, Long> {
    @Query(value = "select follower from following f where f.following=:username", nativeQuery = true)
    List<String> getFollowing(String username);

    @Query(value = "select following from following f where f.follower=:username", nativeQuery = true)
    List<String> getFollowers(String username);
}

package com.examportalservice.auth.user.repo;

import com.examportalservice.auth.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    User findByUsername(String username);
}

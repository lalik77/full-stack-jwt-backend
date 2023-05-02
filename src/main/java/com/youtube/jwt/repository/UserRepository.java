package com.youtube.jwt.repository;

import com.youtube.jwt.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<AppUser, String> {
}

package com.montego.flow.repository;

import com.montego.flow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}

package com.epac.hr.repository;

import com.epac.hr.entity.User;
import com.epac.hr.entity.User.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByUserRole(UserRole userRole);
    List<User> findByIsActive(Boolean isActive);
}

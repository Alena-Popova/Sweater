package org.popova.lesson.sweater.repos;

import org.popova.lesson.sweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

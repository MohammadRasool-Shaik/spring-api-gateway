/**
 *
 */
package org.rash.micro.repository;

import org.rash.micro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Rasool.Shaik
 */
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    Optional<User> findByUserName(String userName);
}

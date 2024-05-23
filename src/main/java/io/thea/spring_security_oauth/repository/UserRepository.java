package io.thea.spring_security_oauth.repository;

import io.thea.spring_security_oauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByOauthProviderAndOauthProviderId(String oauthProvider, String oauthProviderId);
}

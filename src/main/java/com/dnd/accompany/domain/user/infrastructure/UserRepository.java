package com.dnd.accompany.domain.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dnd.accompany.domain.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.provider = :provider AND u.oauthId = :oauthId AND u.deleted = false")
    Optional<User> findUserByProviderAndOauthId(@Param("provider") String provider, @Param("oauthId") String oauthId);
}

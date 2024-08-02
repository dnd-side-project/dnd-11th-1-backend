package com.dnd.accompany.domain.accompany.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dnd.accompany.domain.accompany.entity.AccompanyUsers;

public interface AccompanyUsersRepository extends JpaRepository<AccompanyUsers, Long> {
}

package com.dnd.accompany.domain.accompany.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dnd.accompany.domain.accompany.entity.AccompanyUser;
import com.dnd.accompany.domain.accompany.entity.enums.Role;

@Repository
public interface AccompanyUserRepository extends JpaRepository<AccompanyUser, Long> {
	boolean existsByUserIdAndAccompanyBoardIdAndRole(Long userId, Long accompanyBoardId, Role role);
}

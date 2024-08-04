package com.dnd.accompany.domain.accompany.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dnd.accompany.domain.accompany.entity.AccompanyUser;
import com.dnd.accompany.domain.accompany.entity.enums.Role;
import com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces.AccompanyBoardRepositoryCustom;

@Repository
public interface AccompanyUserRepository extends JpaRepository<AccompanyUser, Long>, AccompanyBoardRepositoryCustom {
	boolean existsByUserIdAndAccompanyBoardIdAndRole(Long userId, Long accompanyBoardId, Role role);
}

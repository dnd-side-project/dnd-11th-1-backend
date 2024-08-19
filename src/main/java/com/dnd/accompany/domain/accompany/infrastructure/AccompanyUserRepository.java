package com.dnd.accompany.domain.accompany.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dnd.accompany.domain.accompany.entity.AccompanyUser;
import com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces.AccompanyUserRepositoryCustom;

public interface AccompanyUserRepository extends JpaRepository<AccompanyUser, Long>, AccompanyUserRepositoryCustom {
	void deleteByAccompanyBoardId(Long boardId);

	@Query("SELECT u.user.id FROM AccompanyUser u WHERE u.accompanyBoard.id = :boardId")
	Optional<Long> findUserIdByAccompanyBoardId(Long boardId);
}

package com.dnd.accompany.domain.accompany.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dnd.accompany.domain.accompany.entity.AccompanyBoards;

public interface AccompanyBoardsRepository extends JpaRepository<AccompanyBoards, Long> {
}

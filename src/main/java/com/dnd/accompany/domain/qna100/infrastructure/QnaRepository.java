package com.dnd.accompany.domain.qna100.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dnd.accompany.domain.qna100.entity.Qna100;
import com.dnd.accompany.domain.qna100.infrastructure.querydsl.interfaces.QnaRepositoryCustom;

@Repository
public interface QnaRepository extends JpaRepository<Qna100, Long>, QnaRepositoryCustom {
	Optional<Qna100> findFirstByUserId(Long userId);

	void deleteByIdIn(List<Long> ids);

	List<Qna100> findAllByUserId(Long userId);
}

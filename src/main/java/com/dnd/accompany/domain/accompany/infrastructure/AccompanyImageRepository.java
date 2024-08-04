package com.dnd.accompany.domain.accompany.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dnd.accompany.domain.accompany.entity.AccompanyImage;
import com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces.AccompanyBoardRepositoryCustom;

@Repository
public interface AccompanyImageRepository extends JpaRepository<AccompanyImage, Long>, AccompanyBoardRepositoryCustom {
}

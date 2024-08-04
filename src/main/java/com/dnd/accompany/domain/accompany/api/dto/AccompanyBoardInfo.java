package com.dnd.accompany.domain.accompany.api.dto;

import java.time.LocalDateTime;

import com.dnd.accompany.domain.accompany.entity.enums.Region;

import lombok.Getter;

@Getter
public class AccompanyBoardInfo {
	private Long id;
	private String title;
	private Region region;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String nickname;
}
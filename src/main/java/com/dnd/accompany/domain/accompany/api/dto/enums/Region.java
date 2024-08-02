package com.dnd.accompany.domain.accompany.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Region {
	SEOUL("서울"),
	GYEONGGI_INCHEON("경기·인천"),
	CHUNGCHEONG_DAEJEON_SEJONG("충청·대전·세종"),
	GANGWON("강원"),
	JEOLLA_GWANGJU("전라·광주"),
	GYEONGSANG_DAEGU_ULSAN("경상·대구·울산"),
	BUSAN("부산"),
	JEJU("제주");

	private final String korean;

	Region(String korean) {
		this.korean = korean;
	}

	@JsonValue
	public String getKorean() {
		return korean;
	}

	@JsonCreator
	public static Region fromKorean(String korean) {
		for (Region region : Region.values()) {
			if (region.getKorean().equals(korean)) {
				return region;
			}
		}
		throw new IllegalArgumentException(korean + "은 지역 목록에 없습니다.");
	}
}

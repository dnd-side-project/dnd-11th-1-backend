package com.dnd.accompany.domain.review.entity.enums;

public enum TravelPreference {
    PLANNED("계획적입니다"),
    SPONTANEOUS("즉흥적입니다"),
    PUBLIC_MONEY_CONVENIENT("공동 비용이 편합니다"),
    DUTCH_PAY("더치페이 합니다"),
    DILIGENT("부지런합니다"),
    RELAXED("여유롭습니다");

    private final String description;

    TravelPreference(String description) {
        this.description = description;
    }
}
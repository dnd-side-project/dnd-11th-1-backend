package com.dnd.accompany.domain.review.entity;

import com.dnd.accompany.domain.common.entity.TimeBaseEntity;
import com.dnd.accompany.domain.review.entity.enums.TravelPreferenceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review_travel_preference")
@SQLRestriction("deleted = false")
@SQLDelete(sql = "UPDATE review_travel_preference SET deleted = true WHERE id = ?")
public class TravelPreference extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TravelPreferenceType type;

    private boolean deleted = Boolean.FALSE;
}

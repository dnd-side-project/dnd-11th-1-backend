package com.dnd.accompany.domain.user.entity;

import com.dnd.accompany.domain.user.entity.enums.FoodPreference;
import com.dnd.accompany.domain.user.entity.enums.Gender;
import com.dnd.accompany.domain.user.entity.enums.Grade;
import com.dnd.accompany.domain.user.entity.enums.TravelPreference;
import com.dnd.accompany.domain.user.entity.enums.TravelStyle;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

import static com.dnd.accompany.domain.user.entity.enums.Grade.ROOKIE;

@Table(name = "user_profiles")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("deleted = false")
@SQLDelete(sql = "UPDATE user_profiles SET deleted = true WHERE id = ?")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(length = 50)
    private String description;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int birthYear;

    @Column
    private String socialMediaUrl;

    private Grade grade = ROOKIE;

    @Builder.Default
    @ElementCollection(targetClass = TravelPreference.class)
    @Enumerated(EnumType.STRING)
    private List<TravelPreference> travelPreferences = new ArrayList<>();

    @Builder.Default
    @ElementCollection(targetClass = TravelStyle.class)
    @Enumerated(EnumType.STRING)
    private List<TravelStyle> travelStyles = new ArrayList<>();

    @Builder.Default
    @ElementCollection(targetClass = FoodPreference.class)
    private List<FoodPreference> foodPreferences = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;

    public void updateUserProfile(
            String description,
            Gender gender,
            int birthYear,
            List<TravelPreference> travelPreferences,
            List<TravelStyle> travelStyles,
            List<FoodPreference> foodPreferences,
            String socialMediaUrl
    ) {
        this.description = description;
        this.gender = gender;
        this.birthYear = birthYear;
        this.travelPreferences = travelPreferences;
        this.travelStyles = travelStyles;
        this.foodPreferences = foodPreferences;
        this.socialMediaUrl = socialMediaUrl;
    }
}

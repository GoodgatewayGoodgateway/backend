package com.roomit.demo.domain.listing;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "facility_summary")
@Getter
@Setter
public class FacilitySummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id")
    private Listing listing;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String name;
    private double lat;
    private double lng;

    public enum Category {
        카페, 편의점, 헬스장
    }
}

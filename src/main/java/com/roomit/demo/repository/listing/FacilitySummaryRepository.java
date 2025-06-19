package com.roomit.demo.repository.listing;

import com.roomit.demo.domain.listing.FacilitySummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilitySummaryRepository extends JpaRepository<FacilitySummary, Long> {
    List<FacilitySummary> findByListingId(Long listingId);
}

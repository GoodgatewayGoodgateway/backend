package com.roomit.demo.service.listing;

import com.roomit.demo.domain.listing.FacilitySummary;
import com.roomit.demo.domain.listing.Listing;
import com.roomit.demo.dto.listing.ListingRequest;
import com.roomit.demo.repository.listing.FacilitySummaryRepository;
import com.roomit.demo.repository.listing.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;
    private final FacilitySummaryRepository facilityRepository;

    public Listing save(ListingRequest dto) {
        Listing listing = new Listing();
        listing.setAddress(dto.getAddress());
        listing.setLat(dto.getLat());
        listing.setLng(dto.getLng());
        listing.setArea(dto.getArea());
        listing.setPrice(dto.getPrice());
        listing.setType(dto.getType());
        return listingRepository.save(listing);
    }

    public List<Listing> findAll() {
        return listingRepository.findAll();
    }

    public Listing findOne(Long id) {
        return listingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("매물 ID 없음: " + id));
    }

    public void delete(Long id) {
        listingRepository.deleteById(id);
    }

    public List<FacilitySummary> getFacilities(Long listingId) {
        return facilityRepository.findByListingId(listingId);
    }
}

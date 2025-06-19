package com.roomit.demo.dto.listing;

import com.roomit.demo.domain.listing.Listing;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListingRequest {
    private String address;
    private double lat;
    private double lng;
    private float area;
    private int price;
    private Listing.Type type;
}

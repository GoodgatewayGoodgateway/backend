package com.roomit.demo.controller;

import com.roomit.demo.domain.listing.Listing;
import com.roomit.demo.domain.listing.FacilitySummary;
import com.roomit.demo.dto.listing.ListingRequest;
import com.roomit.demo.service.listing.ListingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Listing API", description = "매물 등록, 조회, 삭제 및 주변 편의시설 정보 API")
@RestController
@RequestMapping("/api/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

    @Operation(
            summary = "매물 등록",
            description = "새로운 매물 정보를 등록합니다. 주소, 좌표, 면적, 가격, 유형을 포함하여 JSON 형태로 전달하세요."
    )
    @PostMapping
    public ResponseEntity<Listing> register(@RequestBody ListingRequest dto) {
        Listing saved = listingService.save(dto);
        return ResponseEntity.ok(saved);
    }

    @Operation(
            summary = "전체 매물 목록 조회",
            description = "등록된 모든 매물 정보를 리스트로 반환합니다."
    )
    @GetMapping
    public ResponseEntity<List<Listing>> getAll() {
        return ResponseEntity.ok(listingService.findAll());
    }

    @Operation(
            summary = "매물 상세 조회",
            description = "매물 ID를 통해 해당 매물의 상세 정보를 반환합니다."
    )
    @GetMapping("/{id}")
    public ResponseEntity<Listing> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(listingService.findOne(id));
    }

    @Operation(
            summary = "매물 삭제",
            description = "매물 ID를 전달하여 해당 매물을 삭제합니다."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        listingService.delete(id);
        return ResponseEntity.ok().body("매물 삭제 완료");
    }

    @Operation(
            summary = "편의시설 목록 조회",
            description = "매물 ID를 기준으로 해당 매물 주변의 편의시설 정보를 리스트로 반환합니다."
    )
    @GetMapping("/{id}/facilities")
    public ResponseEntity<List<FacilitySummary>> getFacilities(@PathVariable Long id) {
        return ResponseEntity.ok(listingService.getFacilities(id));
    }
}

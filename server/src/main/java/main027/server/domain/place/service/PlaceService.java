package main027.server.domain.place.service;

import main027.server.domain.place.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaceService {

    Place createPlace(Place place);

    Place findPlace(Long placeId);
    Page<Place> searchPlace(Pageable pageable, String keyword);

    Page<Place> findPlaces(Pageable pageable, Long categoryId, String sortBy);

    // Page<Place> findPlacesByLikes(Pageable pageable);
    // Page<Place> findPlacesByCategory(Pageable pageable, Long categoryId);
    void deletePlace(Long memberId, Long placeId);

}

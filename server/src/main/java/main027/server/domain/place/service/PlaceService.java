package main027.server.domain.place.service;

import main027.server.domain.place.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceService {

    Place createPlace(Place place);
    Place findPlace(Long placeId);
    Page<Place> findPlaces(Pageable pageable);
    void deletePlace(Long placeId);


}

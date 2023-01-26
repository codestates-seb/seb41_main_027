package main027.server.domain.place.service;

import main027.server.domain.place.entity.Place;

public interface PlaceUpdateService {
    Place updatePlace(Long memberId, Place place);
}

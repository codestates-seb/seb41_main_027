package main027.server.domain.place.service;

import lombok.RequiredArgsConstructor;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.repository.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Place createPlace(Place place) {return placeRepository.save(place);}

    public Place findPlace(Long placeId) {return findVerifiedPlace(placeId);}

    public Place updatePlace(Place place) {
        Place findPlace = findVerifiedPlace(place.getPlaceId());
        Optional.ofNullable(place.getDescription()).ifPresent(description -> findPlace.setDescription(description));
        return placeRepository.save(findPlace);
    }

    public void deletePlace(Long placeId) {
        placeRepository.deleteById(placeId);
    }


    public Place findVerifiedPlace(Long placeId) {
        Optional<Place> optionalPlace = placeRepository.findById(placeId);
        Place findPlace = optionalPlace.orElseThrow(() ->
                new NoSuchElementException("No Such Place"));
        return findPlace;
    }
}

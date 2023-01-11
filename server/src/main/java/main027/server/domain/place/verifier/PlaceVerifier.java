package main027.server.domain.place.verifier;

import lombok.RequiredArgsConstructor;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.repository.PlaceRepository;
import main027.server.global.exception.BusinessLogicException;
import main027.server.global.exception.ExceptionCode;

import java.util.Optional;

@RequiredArgsConstructor
public class PlaceVerifier {

    private final PlaceRepository placeRepository;

    public Place findVerifiedPlace(Long placeId) {
        Optional<Place> optionalPlace = placeRepository.findById(placeId);
        Place findPlace = optionalPlace.orElseThrow(() ->
                                                            new BusinessLogicException(ExceptionCode.PLACE_NOT_FOUND));
        return findPlace;
    }

    public void verifyExistsPlace(String name) {
        Optional<Place> place = placeRepository.findByName(name);
        if (place.isPresent())
            throw new BusinessLogicException(ExceptionCode.PLACE_ALREADY_EXISTS);

    }
}

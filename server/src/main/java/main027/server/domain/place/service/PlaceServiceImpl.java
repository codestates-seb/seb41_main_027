package main027.server.domain.place.service;

import lombok.RequiredArgsConstructor;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.repository.PlaceRepository;
import main027.server.domain.place.verifier.PlaceVerifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceVerifier placeVerifier;

    public PlaceServiceImpl (PlaceRepository placeRepository, PlaceVerifier placeVerifier) {
        this.placeRepository = placeRepository;
        this.placeVerifier = placeVerifier;
    }

    public Place createPlace(Place place) {
        placeVerifier.verifyExistsPlace(place.getName());
        return placeRepository.save(place);
    }

    public Place findPlace(Long placeId) {
        return placeVerifier.findVerifiedPlace(placeId);
    }

    public Page<Place> findPlaces(Pageable pageable) {
        return placeRepository.findAll(pageable);
    }

    public void deletePlace(Long placeId) {
        Place verifiedPlace = placeVerifier.findVerifiedPlace(placeId);
        placeRepository.delete(verifiedPlace);
    }

}

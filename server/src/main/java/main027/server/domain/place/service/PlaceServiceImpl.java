package main027.server.domain.place.service;

import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.repository.PlaceRepository;
import main027.server.domain.place.verifier.PlaceVerifier;
import main027.server.global.advice.exception.BusinessLogicException;
import main027.server.global.advice.exception.ExceptionCode;
import main027.server.global.advice.exception.PermissionDeniedException;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceVerifier placeVerifier;

    public PlaceServiceImpl(PlaceRepository placeRepository, PlaceVerifier placeVerifier) {
        this.placeRepository = placeRepository;
        this.placeVerifier = placeVerifier;
    }

    @TimeTrace
    public Place createPlace(Place place) {
        placeVerifier.verifyExistsPlace(place.getName());
        return placeRepository.save(place);
    }

    @TimeTrace
    public Page<Place> searchPlace(Pageable pageable, String keyword) {
       if (keyword.length() < 2) {
           throw new BusinessLogicException(ExceptionCode.SHORT_KEYWORD);
       }

        return placeRepository.searchPlacesByKeyword(pageable, keyword);
    }

    @TimeTrace
    public Place findPlace(Long placeId) {
        return placeVerifier.findVerifiedPlace(placeId);
    }

    @TimeTrace
    public Page<Place> findPlaces(Pageable pageable, Long categoryId, String sortBy) {
        if (categoryId == null) {
            if (sortBy.equals("time"))
                return placeRepository.findAllCreatedAt(pageable);
            else return placeRepository.findAllLikeCount(pageable);
        }

        if (sortBy.equals("time"))
            return placeRepository.findByCategoryCreatedAt(pageable, categoryId);
        else return placeRepository.findByCategoryLikeCount(pageable, categoryId);

    }

    @TimeTrace
    public void deletePlace(Long memberId, Long placeId) {
        Place findPlace = placeVerifier.findVerifiedPlace(placeId);
        if (!Objects.equals(findPlace.getMember().getMemberId(), memberId)) {
            throw new PermissionDeniedException(ExceptionCode.PERMISSION_DENIED);
        }
        placeRepository.delete(findPlace);
    }

}

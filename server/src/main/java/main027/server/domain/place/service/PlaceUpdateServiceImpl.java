package main027.server.domain.place.service;

import lombok.RequiredArgsConstructor;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.repository.PlaceRepository;
import main027.server.domain.place.verifier.PlaceVerifier;
import main027.server.global.advice.exception.ExceptionCode;
import main027.server.global.advice.exception.PermissionDeniedException;
import main027.server.global.aop.logging.annotation.TimeTrace;
import main027.server.global.utils.CustomBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceUpdateServiceImpl implements PlaceUpdateService {

    private final PlaceRepository placeRepository;
    private final CustomBeanUtils<Place> beanUtils;
    private final PlaceVerifier placeVerifier;

    @TimeTrace
    public Place updatePlace(Long memberId, Place place) {
        Place verifiedPlace = placeVerifier.findVerifiedPlace(place.getPlaceId());
        if (!Objects.equals(verifiedPlace.getMember().getMemberId(), memberId)) {
            throw new PermissionDeniedException(ExceptionCode.PERMISSION_DENIED);
        }
        Place updatedPlace = beanUtils.copyNonNullProperties(place, verifiedPlace);
        return placeRepository.save(updatedPlace);
    }
}

package main027.server.domain.place.service;

import lombok.RequiredArgsConstructor;
import main027.server.domain.member.verifier.MemberVerifier;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.entity.PlaceLikeUser;
import main027.server.domain.place.repository.PlaceLikeUserRepository;
import main027.server.domain.place.verifier.PlaceVerifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceLikeServiceImpl implements PlaceLikeService {

    private final PlaceLikeUserRepository placeLikeUserRepository;
    private final MemberVerifier memberVerifier;
    private final PlaceVerifier placeVerifier;

    public Boolean changeLikeUserStatus(PlaceLikeUser placeLikeUser) {
        memberVerifier.findVerifiedMember(placeLikeUser.getMember().getMemberId());
        placeVerifier.findVerifiedPlace(placeLikeUser.getPlace().getPlaceId());

        Optional<PlaceLikeUser> findLikeUser = placeLikeUserRepository
                .checkLiked(placeLikeUser.getMember().getMemberId(),
                            placeLikeUser.getPlace().getPlaceId());

        boolean isLiked = findLikeUser.isPresent();

        if (isLiked) {
            placeLikeUserRepository.delete(findLikeUser.get());
            return false;
        } else {
            placeLikeUserRepository.save(placeLikeUser);
            return true;
        }
    }

    public Page<Place> findPlaceMemberLiked(Long memberId, Pageable pageable) {
        memberVerifier.findVerifiedMember(memberId);
        return placeLikeUserRepository.findLikes(memberId, pageable);
    }
}

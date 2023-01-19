package main027.server.domain.place.service;

import main027.server.domain.place.entity.Place;
import main027.server.domain.place.entity.PlaceLikeUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceLikeService {
    Boolean changeLikeUserStatus(PlaceLikeUser placeLikeUser);

    Page<Place> findPlaceMemberLiked(Long memberId, Pageable pageable);

    Boolean isLike(Long memberId, Long placeId);
}

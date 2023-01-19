package main027.server.domain.place.repository;

import io.lettuce.core.dynamic.annotation.Param;
import main027.server.domain.place.entity.Place;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    @TimeTrace
    Optional<Place> findByName(String name);
    @Query("select p from Place p where p.category.categoryId = :categoryId order by p.placeLikeUserList.size desc")
    Page<Place> CategoryId(Pageable pageable, @Param("categoryId") Long categoryId);

    @Query("select p from Place p order by p.placeLikeUserList.size desc")
    Page<Place> likeDesc(Pageable pageable);
}

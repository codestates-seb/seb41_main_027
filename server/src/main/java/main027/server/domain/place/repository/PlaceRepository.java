package main027.server.domain.place.repository;

import io.lettuce.core.dynamic.annotation.Param;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    @TimeTrace
    Optional<Place> findByName(String name);

    @TimeTrace
    @Query("select p from Place p where p.name like %:keyword% order by p.placeLikeUserList.size desc")
    Page<Place> searchPlacesByKeyword(Pageable pageable, @Param("keyword") String keyword);

    @TimeTrace
    @Query("select p from Place p where p.category.categoryId = :categoryId order by p.placeLikeUserList.size desc")
    Page<Place> CategoryId(Pageable pageable, @Param("categoryId") Long categoryId);

    @TimeTrace
    @EntityGraph(attributePaths = {"category", "bookmarkList", "placeLikeUserList"})
    @Query("select p from Place p order by p.placeLikeUserList.size desc")
    Page<Place> findAllLikeCount(Pageable pageable);

    @TimeTrace
    @EntityGraph(attributePaths = {"category", "bookmarkList", "placeLikeUserList"})
    @Query("select p from Place p order by p.createdAt desc")
    Page<Place> findAllCreatedAt(Pageable pageable);

    /**
     * 장소 리스트 카테고리로 분류 및 정렬
     */
    @TimeTrace
    @Query("select p from Place p where p.category.categoryId = :categoryId order by p.placeLikeUserList.size desc")
    Page<Place> findByCategoryLikeCount(Pageable pageable, @Param("categoryId") Long categoryId);

    @TimeTrace
    @Query("select p from Place p where p.category.categoryId = :categoryId order by p.createdAt desc")
    Page<Place> findByCategoryCreatedAt(Pageable pageable, @Param("categoryId") Long categoryId);

}

package main027.server.domain.place.repository;

import main027.server.domain.place.entity.Place;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    @TimeTrace
    Optional<Place> findByName(String name);
}

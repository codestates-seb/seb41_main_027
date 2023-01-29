package main027.server.domain.place.repository;

import main027.server.domain.member.entity.Member;
import main027.server.domain.place.entity.Place;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.naming.Name;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PlaceRepositoryTest {
    @Autowired
    private PlaceRepository placeRepository;

//    @Test
//    public void savePlaceTest() {
//        //given
//        Place place = new Place();
//        place.setPlaceId(1L);
//        place.setName("길이식당");
//        place.setAddress("서울시 광진구");
//        place.setDescription("맛있어요");
//
//        //when
//        Place savePlace = placeRepository.save(place);
//
//        //then
//        assertNotNull(savePlace);
//        assertTrue(place.getPlaceId().equals(savePlace.getPlaceId()));
//        assertTrue(place.getName().equals(savePlace.getName()));
//        assertTrue(place.getAddress().equals(savePlace.getAddress()));
//        assertTrue(place.getDescription().equals(savePlace.getDescription()));
//    }

    @Test
    public void findByNameTest(){
        //given
        Place place = new Place();
        place.setPlaceId(1L);
        place.setName("길이식당");
        place.setAddress("서울시 광진구");
        place.setDescription("맛있어요");

        //when
        placeRepository.save(place);
        Optional<Place> findPlace = placeRepository.findByName(place.getName());

        //then
        assertThat(findPlace.isPresent());
        assertThat(findPlace.get().getName().equals(place.getName()));

    }

    @Test
    public void searchPlacesByKeywordTest() {
//
//
//        Place place1 = new Place();
//        place1.setName("길이식당");
//        place1.setAddress("서울시 광진구");
//        place1.setDescription("맛있어요");
//
//
//        Place place2 = new Place();
//        place2.setName("둘리식당");
//        place2.setAddress("서울시 광진구");
//        place2.setDescription("맛없어요");
//
//
//
//        Pageable pageable = PageRequest.of(1,5);
//
//
//
//        placeRepository.save(place1);
//        placeRepository.save(place2);
//
//        Page<Place> searchPlaceByKeyword = placeRepository.searchPlacesByKeyword(pageable, "길이");
//
//        assertTrue(searchPlaceByKeyword.getContent().equals(place1));

    }

    @Test
    public void findAllLikeCountTest() {

    }

    @Test
    public void findAllCreatedAtTest() {

    }

    @Test
    public void findByCategoryLikeCountTest() {

    }

    @Test
    public void findByCategoryCreatedAtTest() {

    }

}

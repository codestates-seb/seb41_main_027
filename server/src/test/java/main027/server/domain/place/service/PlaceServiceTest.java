package main027.server.domain.place.service;

import main027.server.domain.member.entity.Member;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.repository.PlaceRepository;
import main027.server.domain.place.verifier.PlaceVerifier;
import main027.server.global.advice.exception.BusinessLogicException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class PlaceServiceTest {

    @Mock
    private PlaceService placeService;
    @Mock
    private PlaceRepository placeRepository;
    @Mock
    private PlaceVerifier placeVerifier;

    @Test
    public void createPlaceTest() {

        Place place = new Place();
        place.setPlaceId(1L);
        place.setName("길이식당");
        place.setDescription("맛없어요");

        given(placeService.createPlace(place)).willReturn(place);

        assertThat(place).isEqualTo(placeService.createPlace(place));

    }

//    @Test
//    public void searchPlaceTest() {
//
//        Place place = new Place();
//        place.setPlaceId(1L);
//        place.setName("길이식당");
//        place.setDescription("맛없어요");
//
//        List<Place> places = List.of();
//
//        Page<Place> places = new PageImpl<>(List.of())
//
//    }

    @Test
    public void findPlaceTest() {

        Place place = new Place();
        place.setPlaceId(1L);
        place.setName("길이식당");
        place.setDescription("맛없어요");

        given(placeService.findPlace(1L)).willReturn(place);

        assertThat(place).isEqualTo(placeService.findPlace(1L));
    }

    @Test
    public void deletePlaceTest() {

        Place place = new Place();
        place.setPlaceId(1L);
        place.setName("길이식당");
        place.setDescription("맛없어요");

        placeRepository.save(place);
        placeRepository.delete(place);

        assertThat(placeService.findPlace(1L)).isNull();

    }

}

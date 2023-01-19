package main027.server.domain.place.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import main027.server.domain.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PlaceLikeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeLikerUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "place_id")
    private Place place;

//    public void setPlace(Place place) {
//        this.place = place;
//        place.getPlaceLikeUserList().add(this);
//    }
}

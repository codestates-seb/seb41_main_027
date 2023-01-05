package main027.server.domain.review.entity;

import main027.server.domain.audit.BaseTime;
import main027.server.domain.place.entity.Place;

import javax.persistence.*;

@Entity(name = "reviews")
public class Review extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @JoinColumn(name = "place_id")
    // private Place place;
    //
    // @JoinColumn(name = "user_id")
    // private User user;

    @Column(length = 40)
    private String content;

    @Column(length = 20)
    private String emoji;

}

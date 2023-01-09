package main027.server.domain.review.entity;

import lombok.*;
import main027.server.domain.audit.BaseTime;
import main027.server.domain.member.entity.Member;
import main027.server.domain.place.entity.Place;

import javax.lang.model.element.Name;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "review")
public class Review extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;


    @Column(length = 40)
    private String content;

    //review -> emoji 단방향 연관관계 매핑 (양방향 X)
    @ManyToOne
    @JoinColumn(name = "emoji_id")
    private Emoji emoji;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        member.getReviewList().add(this);
    }

    public void setPlace(Place place) {
        this.place = place;
        place.getReviewList().add(this);
    }
}

package main027.server.domain.review.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main027.server.domain.audit.BaseTime;
import main027.server.domain.member.entity.Member;
import main027.server.domain.place.entity.Place;

import javax.persistence.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity(name = "review")
public class Review extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 40)
    private String content;

    @Column(length = 20)
    private String emoji;

}

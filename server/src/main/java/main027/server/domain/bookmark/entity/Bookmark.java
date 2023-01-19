package main027.server.domain.bookmark.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main027.server.domain.member.entity.Member;
import main027.server.domain.place.entity.Place;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmarkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    public void setMember(Member member) {
        this.member = member;
        member.getBookmarkList().add(this);
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Bookmark(Member member, Place place) {
        this.member = member;
        this.place = place;
    }
}

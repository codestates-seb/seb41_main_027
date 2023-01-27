package main027.server.domain.place.entity;


import lombok.Data;
import main027.server.domain.bookmark.entity.Bookmark;
import main027.server.domain.member.entity.Member;
import main027.server.domain.review.entity.Review;
import main027.server.global.audit.BaseTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Place extends BaseTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    /**
     * 카카오 API ID
     */
    @Column
    private Long kakaoId;

    /**
     * 위도
     */
    @Column
    private String latitude;

    /**
     * 경도
     */
    @Column
    private String longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // place가 삭제되면 해당 place의 리뷰는 삭제되는게 맞으므로 cascade = REMOVE
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    private List<Review> reviewList = new ArrayList<>();

    // place가 삭제되면 해당 palce가 속해 있는 북마크는 삭제된다.
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Bookmark> bookmarkList = new HashSet<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<PlaceLikeUser> placeLikeUserList = new HashSet<>();

    public void setMember(Member member) {
        this.member = member;
        member.getPlaceList().add(this);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getPlaceList().add(this);
    }
}

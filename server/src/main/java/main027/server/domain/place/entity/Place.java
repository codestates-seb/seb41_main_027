package main027.server.domain.place.entity;


import lombok.Data;
import main027.server.domain.audit.BaseTime;
import main027.server.domain.member.entity.Member;
import main027.server.domain.review.entity.Review;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Place extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Long likeCount = 0L;

    /**
     * 카카오 API ID
     */
    @Column(nullable = false, unique = true)
    private Long kakaoId;

    /**
     * 위도
     */
    @Column(nullable = false)
    private Long latitude;

    /**
     * 경도
     */
    @Column(nullable = false)
    private Long longitude;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();
//
//    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
//    private List<Bookmark> bookmarks = new ArrayList<>();
//
//    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
//    private List<Tag> tags = new ArrayList<>();
//
//    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
//    private List<PlaceLikeUser> placeLikeUsers = new ArrayList<>();


}

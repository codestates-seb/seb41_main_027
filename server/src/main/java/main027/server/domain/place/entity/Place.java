package main027.server.domain.place.entity;


import lombok.Data;
import main027.server.domain.audit.BaseTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "PLACE")
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
     * 위도
     */
    @Column(nullable = false, unique = true)
    private Long latitude;

    /**
     * 경도
     */
    @Column(nullable = false, unique = true)
    private Long longitude;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
//    private List<Review> reviews = new ArrayList<>();
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

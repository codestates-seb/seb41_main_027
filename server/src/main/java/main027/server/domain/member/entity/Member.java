package main027.server.domain.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main027.server.domain.bookmark.entity.Bookmark;
import main027.server.domain.place.entity.Place;
import main027.server.domain.review.entity.Review;
import main027.server.global.audit.BaseTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickName;

    // member가 삭제 될 때 place정보는 남겨둬야하는 것이 필요하므로, 해당 로직 작성 필요
    @OneToMany(mappedBy = "member")
    private List<Place> placeList = new ArrayList<>();

    // member가 삭제될 때, 해당 member가 작성한 리뷰도 같이 삭제 (논의 필요)
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Review> reviewList = new ArrayList<>();

    // member가 삭제되면 해당 member의 북마크는 삭제되어진다.
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public Member(String email, String password, String nickName) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }
}

package main027.server.domain.review.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
public class Emoji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emojiId;

    // 이모지의 이름
    private String name;

}

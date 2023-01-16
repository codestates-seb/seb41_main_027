package main027.server.domain.review.repository;

import main027.server.domain.review.entity.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmojiRepository extends JpaRepository<Emoji, Long> {
}

package main027.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import main027.server.domain.review.entity.Emoji;
import main027.server.domain.review.repository.EmojiRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmojiService {

    private final EmojiRepository emojiRepository;

    public void verifyExistEmoji(Long emojiId) {
        emojiRepository.findById(emojiId).orElseThrow(
                () -> new RuntimeException("Emoji Not Found")); // 예외처리 리팩터링 필요
    }
}

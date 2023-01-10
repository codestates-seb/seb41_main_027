package main027.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import main027.server.domain.review.entity.Emoji;
import main027.server.domain.review.repository.EmojiRepository;
import main027.server.global.exception.BusinessLogicException;
import main027.server.global.exception.ExceptionCode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmojiService {

    private final EmojiRepository emojiRepository;

    public void verifyExistEmoji(Long emojiId) {
        emojiRepository.findById(emojiId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.EMOJI_NOT_FOUND));
    }
}

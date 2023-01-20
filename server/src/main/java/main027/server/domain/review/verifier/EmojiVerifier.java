package main027.server.domain.review.verifier;

import lombok.RequiredArgsConstructor;
import main027.server.domain.review.entity.Emoji;
import main027.server.domain.review.repository.EmojiRepository;
import main027.server.global.advice.exception.BusinessLogicException;
import main027.server.global.advice.exception.ExceptionCode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmojiVerifier {

    private final EmojiRepository emojiRepository;

    public Emoji findVerifiedEmoji(Long emojiId) {
        return emojiRepository.findById(emojiId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.EMOJI_NOT_FOUND));
    }
}

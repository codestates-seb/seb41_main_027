package main027.server.global.auth.Redis;

import lombok.RequiredArgsConstructor;
import main027.server.global.advice.exception.ExceptionCode;
import main027.server.global.advice.exception.TokenException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;

    /** RefreshToken Redis에 저장 */
    public void setRefreshToken(String key, String value, long expirationMinutes) {
        if (key.startsWith("Bearer")) throw new TokenException(ExceptionCode.INVALID_REFRESH_TOKEN);
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, value, Duration.ofMinutes(expirationMinutes));
    }

    /** Token Redis에서 가져오기 */
    public String getToken(String key) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(key);
    }

    /** AccessToken Redis에 저장 */
    public void setBlackList(String key, String value, long expirationMinutes) {
        if (!key.startsWith("Bearer")) throw new TokenException(ExceptionCode.INVALID_ACCESS_TOKEN);
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, value, Duration.ofMinutes(expirationMinutes));
    }

    /** 로그아웃 후 RefreshToken 삭제 */
    public void deleteRefreshToken(String key) {
        redisTemplate.delete(key);
    }
}

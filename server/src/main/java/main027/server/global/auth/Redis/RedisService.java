package main027.server.global.auth.Redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;

    public void setRefreshToken(String key, String value, long timeout) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, value, timeout);
    }

    public String getRefreshToken(String key) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(key);
    }

    public void deleteRefreshToken(String key) {
        redisTemplate.delete(key);
    }
}
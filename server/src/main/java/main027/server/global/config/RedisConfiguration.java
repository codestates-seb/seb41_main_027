package main027.server.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@RequiredArgsConstructor
@Configuration
@EnableRedisRepositories
public class RedisConfiguration {
    private final RedisProperties redisProperties;


    public ObjectMapper objectMapper() {
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator
                .builder()
                .allowIfSubType(Object.class)
                .build();

        ObjectMapper mapper = new ObjectMapper();

        mapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        mapper.registerModules(new JavaTimeModule(), new Jdk8Module());
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

        return mapper;
    }

    /**
     * <p>lettuce??? ??????(Jedis??? ???????????? ???????????? lettuce ?????? ??? ??????????????? ???????????????, lettuce??? ?????? ????????? ??????)</p>
     * RedisConnectionFactory : redis??? connection??? ??????????????? ??????
     * RedisTemplate : redis????????? ????????? ????????????, ???????????? ????????? redis ????????? ???????????? ????????? ????????? ????????? ??????
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisProperties.getHost(),
                                            redisProperties.getPort());
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager.RedisCacheManagerBuilder builder =
                RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory());
        RedisCacheConfiguration configuration =
                RedisCacheConfiguration.defaultCacheConfig()
                                       .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(
                                               new StringRedisSerializer()))
                                       .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                                               new GenericJackson2JsonRedisSerializer(objectMapper())))
                                       .entryTtl(Duration.ofMinutes(30));

        builder.cacheDefaults(configuration);
        return builder.build();
    }
}


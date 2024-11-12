package tayduong.com.employeebe.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tayduong.com.employeebe.dto.EmployeeDto;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableCaching
public class CacheConfig {
    private static final String CACHE_PREFIX = "cache::";
    private static final String EMPLOYEE_CACHE = "employees";
    private static final String EMPLOYEE_SET_CACHE = "employeeSets";

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisBuilderCustomizer(ObjectMapper objectMapper) {
        // Create a copy of ObjectMapper for cache serialization
        ObjectMapper cacheObjectMapper = objectMapper.copy();
        cacheObjectMapper.activateDefaultTyping(
                cacheObjectMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        // Default serializer for other cache entries
        GenericJackson2JsonRedisSerializer defaultSerializer =
                new GenericJackson2JsonRedisSerializer(cacheObjectMapper);

        // Specific serializers for each model
        Jackson2JsonRedisSerializer<EmployeeDto> employeeSerializer =
                new Jackson2JsonRedisSerializer<>(cacheObjectMapper, EmployeeDto.class);

        // Specific serializers for each model
// Use Jackson2JsonRedisSerializer with a configured type and ObjectMapper in the constructor
        Jackson2JsonRedisSerializer<HashSet<EmployeeDto>> employeeSetSerializer =
                new Jackson2JsonRedisSerializer<>(cacheObjectMapper.getTypeFactory().constructCollectionType(HashSet.class, EmployeeDto.class));

        return builder -> builder
                // Default cache configuration
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                        .prefixCacheNameWith(CACHE_PREFIX)
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(defaultSerializer))
                        .entryTtl(Duration.ofDays(1)))
                // Employee cache configuration
                .withCacheConfiguration(EMPLOYEE_CACHE, RedisCacheConfiguration.defaultCacheConfig()
                        .prefixCacheNameWith(CACHE_PREFIX)
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(employeeSerializer))
                        .entryTtl(Duration.ofHours(1)))// Employee cache configuration
                .withCacheConfiguration(EMPLOYEE_SET_CACHE, RedisCacheConfiguration.defaultCacheConfig()
                        .prefixCacheNameWith(CACHE_PREFIX)
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(employeeSetSerializer))
                        .entryTtl(Duration.ofHours(1)));
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory connectionFactory,
            ObjectMapper objectMapper) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        ObjectMapper redisObjectMapper = objectMapper.copy();
        redisObjectMapper.activateDefaultTyping(
                redisObjectMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        GenericJackson2JsonRedisSerializer serializer =
                new GenericJackson2JsonRedisSerializer(redisObjectMapper);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        template.setDefaultSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
package com.test.restservice.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@EnableCaching
@Configuration
public class RedisConfig {

	@Value("${cache.config.entryTtl:60}")
	private int entryTtl;

	@Value("${cache.config.repositoryDetails.entryTtl:30}")
	private int repositoryDetailsEntryTtl;

	public static final String REPOSITORY_DETAILS = "repositoryDetails";

	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(entryTtl))
				.disableCachingNullValues().serializeValuesWith(RedisSerializationContext.SerializationPair
						.fromSerializer(new GenericJackson2JsonRedisSerializer()));
	}

	@Bean
	public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
		return builder -> {
			var repositoryDetailsConf = RedisCacheConfiguration.defaultCacheConfig()
					.entryTtl(Duration.ofMinutes(repositoryDetailsEntryTtl));
			builder.withCacheConfiguration(REPOSITORY_DETAILS, repositoryDetailsConf);
		};
	}
}

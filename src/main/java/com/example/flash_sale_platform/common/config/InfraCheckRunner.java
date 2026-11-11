package com.example.flash_sale_platform.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class InfraCheckRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(InfraCheckRunner.class);

    private final JdbcTemplate jdbcTemplate;
    private final StringRedisTemplate redisTemplate;

    public InfraCheckRunner(JdbcTemplate jdbcTemplate, StringRedisTemplate redisTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void run(String... args) {
        String pgVersion = jdbcTemplate.queryForObject("SELECT version()", String.class);
        log.info("PostgreSQL connection OK: {}", pgVersion);

        redisTemplate.opsForValue().set("infra:check", "pong");
        String value = redisTemplate.opsForValue().get("infra:check");
        log.info("Redis connection OK: infra:check = {}", value);
    }
}
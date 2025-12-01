package com.todo.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JPAConfig {

    @PersistenceContext //의존성 주입 담당
    private EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        // 쿼리 작성하는 JPAQueryFactory에 Entitymanager 넘겨 사용
        return new JPAQueryFactory(em);
    }
}

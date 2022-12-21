package com.hydroyura.productionmanager.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<Entity, ID> extends JpaRepository<Entity, ID>, QuerydslPredicateExecutor<Entity> {}

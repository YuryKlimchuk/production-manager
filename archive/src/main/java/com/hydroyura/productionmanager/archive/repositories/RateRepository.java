package com.hydroyura.productionmanager.archive.repositories;

import com.hydroyura.productionmanager.archive.entities.DBRate;
import org.springframework.stereotype.Repository;

@Repository(value = "RateRepository")
public interface RateRepository extends BaseRepository<DBRate, Long> {}

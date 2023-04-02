package com.hydroyura.productionmanager.archive.repositories;

import com.hydroyura.productionmanager.archive.entities.DBPartChange;
import org.springframework.stereotype.Repository;

@Repository(value = "PartChangeRepository")
public interface PartChangeRepository extends BaseRepository<DBPartChange, Long> {}

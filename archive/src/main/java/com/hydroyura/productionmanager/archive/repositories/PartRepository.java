package com.hydroyura.productionmanager.archive.repositories;

import com.hydroyura.productionmanager.archive.entities.DBPart;
import org.springframework.stereotype.Repository;

@Repository(value = "PartRepository")
public interface PartRepository extends BaseRepository<DBPart, Long> {}

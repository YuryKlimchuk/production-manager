package com.hydroyura.productionmanager.archive.services.rates;

import com.hydroyura.productionmanager.archive.entities.DBRate;
import com.hydroyura.productionmanager.archive.entities.QDBRate;
import com.hydroyura.productionmanager.archive.repositories.BaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service(value = "RateService")
public class RateService implements IRateService<DBRate, DTORate> {

    private Class<DTORate> dtoType = DTORate.class;

    @Autowired @Qualifier(value = "RateRepository")
    private BaseRepository<DBRate, Long> repository;

    @Autowired
    protected ModelMapper modelMapper;


    @Override
    public Collection<DTORate> getAllByAssemblyId(long id) {
        return
            StreamSupport.stream(repository.findAll(QDBRate.dBRate.assembly.id.eq(id)).spliterator(), false)
                    .map(entity -> modelMapper.map(entity, dtoType))
                    .collect(Collectors.toList());
    }

}

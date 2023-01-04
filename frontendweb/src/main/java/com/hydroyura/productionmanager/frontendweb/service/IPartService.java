package com.hydroyura.productionmanager.frontendweb.service;

import com.hydroyura.productionmanager.sharedapi.dto.DTOPart;
import com.hydroyura.productionmanager.sharedapi.feigninterfaces.IArchivePartRestController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "archive-parts", url = "http://localhost:8100/v1/parts")
public interface IPartService extends IArchivePartRestController<DTOPart> {}

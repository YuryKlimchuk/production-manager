package com.hydroyura.productionmanager.frontendweb.controller;

import com.hydroyura.productionmanager.frontendweb.service.IPartService;
import com.hydroyura.productionmanager.frontendweb.service.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/archive")
public class PartController implements IPartController {

    @Autowired
    private IPartService partService;

    @Autowired
    private IRateService rateService;

    @Override
    public String displayList(String type, Model model) {
        ResponseEntity<?> response = partService.getAll(null);

        model.addAttribute("type", type);

        if(response.getStatusCode().equals(HttpStatus.OK)) {
            model.addAttribute("items", ((List<Map<String, Object>>) response.getBody()));
        }
        return "parts-list";
    }

    @Override
    public String displayItem(String id, Model model) {
        ResponseEntity<?> response = partService.getItemById(id);


        if(response.getStatusCode().equals(HttpStatus.OK)) {
            model.addAttribute("item", response.getBody());
        }

        if(response.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
            return "redirect:/archive/list";
        }
        return "part-detail";
    }

    @Override
    public String displaySpecification(String id, Model model) {
        ResponseEntity<?> response = partService.getItemById(id);
        if(response.getStatusCode().equals(HttpStatus.OK)) {
            Map<String, String> item = (Map<String, String>) response.getBody();
            String type = (item.containsKey("type")) ? item.get("type") : null;

            if(type.equals("ASSEMBLY")) {
                ResponseEntity<?> responseAssembly = rateService.getSpecificationByAssemblyId(id);
                if(responseAssembly.getStatusCode().equals(HttpStatus.OK)) {
                    model.addAttribute("specification", responseAssembly.getBody());
                }
            } else {
                // redirect to list
            }
        }

        return "assembly-specification";
    }

    @Override
    public Collection<Map<String, Object>> getItems(String type) {
        //FIXME: add checking
        return (Collection<Map<String, Object>>) partService.getAll(null).getBody();
    }
}

package com.hydroyura.productionmanager.frontendweb.controller.archive;

import com.hydroyura.productionmanager.frontendweb.controller.AbstractController;
import com.hydroyura.productionmanager.frontendweb.rendered.RenderedFragment;
import com.hydroyura.productionmanager.frontendweb.rendered.RenderedFragmentType;
import com.hydroyura.productionmanager.frontendweb.service.IPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/archive/v1")
public class ArchiveController extends AbstractController {

    static final String URL_DISPLAY_LIST = "/list";
    static final String URL_DISPLAY_ITEM = "/list/{id}";

    @Autowired
    private IPartService partService;


    @Override
    final protected void init2() {
        CONTROLLER_ID = "ARCHIVE_CONTROLLER_ID";
        DEFAULT_VIEW = "base-layout";

        //URLs.putIfAbsent(URL_DISPLAY_ITEM, URL_DISPLAY_ITEM);
        setFragmentToURL(URL_DISPLAY_LIST, new RenderedFragment().setType(RenderedFragmentType.BODY).setSource("parts-list.html").setName("archive-list"));



    }

    @RequestMapping(value = URL_DISPLAY_LIST, method = RequestMethod.GET)
    public String displayList(@RequestParam(name = "type", required = true, defaultValue = "PART") String type, Model model) {
        model.addAttribute("type", type);
        ResponseEntity<?> response = partService.getItemsByType(type);
        if(response.getStatusCode().equals(HttpStatus.OK)) {
            model.addAttribute("items", response.getBody());
        }
        return DEFAULT_VIEW;
    }

    @RequestMapping(value = URL_DISPLAY_ITEM, method = RequestMethod.GET)
    public String displayItem(@RequestParam(name = "type", required = true, defaultValue = "PART") String type, Model model) {
        return DEFAULT_VIEW;
    }



}

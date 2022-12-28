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

        // FIXME: change from MAP to LIST
        URLs.putIfAbsent(URL_DISPLAY_LIST, URL_DISPLAY_LIST);
        RenderedFragment renderedFragment = new RenderedFragment()
                .setType(RenderedFragmentType.BODY)
                .setSource("fragments/default/base-layout-default.html")
                .setName("test1");
        setFragmentToURL(URL_DISPLAY_LIST, renderedFragment);

        URLs.putIfAbsent(URL_DISPLAY_ITEM, URL_DISPLAY_ITEM);
        RenderedFragment renderedFragment2 = new RenderedFragment()
                .setType(RenderedFragmentType.BODY)
                .setSource("parts-list.html")
                .setName("archive-list");
        setFragmentToURL(URL_DISPLAY_ITEM, renderedFragment2);

        /*
        beginScripts.add(
                new RenderedFragment()
                        .setType(RenderedFragmentType.JS_BEGIN)
                        .setName("archive.js")
                        .setSource("/js/")
        );
         */
    }

    @RequestMapping(value = URL_DISPLAY_LIST, method = RequestMethod.GET)
    public String displayList(@RequestParam(name = "type", required = true, defaultValue = "PART") String type, Model model) {

        return DEFAULT_VIEW;
    }

    @RequestMapping(value = URL_DISPLAY_ITEM, method = RequestMethod.GET)
    public String displayItem(@RequestParam(name = "type", required = true, defaultValue = "PART") String type, Model model) {
        ResponseEntity<?> response = partService.getItemsByType(type);

        model.addAttribute("hello", "Yury, you are best employer");

        Map<String, Object> bodyBlockModel = new HashMap<>();
        bodyBlockModel.put("hello", "Нигга Хеллоу");
        model.addAttribute("bodyBlockModel", bodyBlockModel);

        model.addAttribute("type", type);

        if(response.getStatusCode().equals(HttpStatus.OK)) {
            model.addAttribute("items", ((List<Map<String, Object>>) response.getBody()));
        }
        return DEFAULT_VIEW;
    }



}

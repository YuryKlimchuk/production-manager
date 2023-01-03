package com.hydroyura.productionmanager.frontendweb.controller.archive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.productionmanager.frontendweb.controller.AbstractController;
import com.hydroyura.productionmanager.frontendweb.dto.DTOPart;
import com.hydroyura.productionmanager.frontendweb.rendered.RenderedFragment;
import com.hydroyura.productionmanager.frontendweb.rendered.RenderedFragmentType;
import com.hydroyura.productionmanager.frontendweb.service.IPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping(value = "/archive/v1")
public class ArchiveController extends AbstractController {

    static final String URL_DISPLAY_LIST = "/list";
    static final String URL_DISPLAY_ITEM = "/list/{id}";
    static final String URL_CREATE_NEW = "/list/create-new";

    @Autowired
    private IPartService partService;

    @Autowired
    private ObjectMapper objectMapper;


    private Collection<String> dtoKeys = Arrays.asList("number", "name", "status", "type");


    @Override
    final protected void init2() {
        CONTROLLER_ID = "ARCHIVE_CONTROLLER_ID";
        DEFAULT_VIEW = "/archive/base-layout";

        //URLs.putIfAbsent(URL_DISPLAY_ITEM, URL_DISPLAY_ITEM);
        setFragmentToURL(URL_DISPLAY_LIST, new RenderedFragment().setType(RenderedFragmentType.BODY).setSource("/archive/fragments/fragments-archive.html").setName("archive-list"));
        setFragmentToURL(URL_DISPLAY_ITEM, new RenderedFragment().setType(RenderedFragmentType.BODY).setSource("/archive/fragments/fragments-archive.html").setName("archive-detail-item"));
        setFragmentToURL(URL_CREATE_NEW, new RenderedFragment().setType(RenderedFragmentType.BODY).setSource("/archive/fragments/fragments-archive.html").setName("archive-create-new"));

        // archive-create-new

        init1();

    }

    @RequestMapping(value = URL_DISPLAY_LIST, method = RequestMethod.GET)
    public String displayList(Model model, @ModelAttribute(name = "filter") ArchiveSearchFilter filter) {
        // set default value to type filed
        if(filter.getType() == null) filter.setType("PART");

        model.addAttribute("filter", filter);

        var map = objectMapper.convertValue(filter, Map.class);

        ResponseEntity<?> response = partService.getAll(map);
        if(response.getStatusCode().equals(HttpStatus.OK)) {
            model.addAttribute("items", response.getBody());
        }
        return DEFAULT_VIEW;
    }

    @RequestMapping(value = URL_DISPLAY_ITEM, method = RequestMethod.GET)
    public String displayItem(@PathVariable(name = "id") String id, Model model) {
        ResponseEntity<?> response = partService.getItemById(id);

        if(response.getStatusCode().equals(HttpStatus.OK)) {
            model.addAttribute("item", response.getBody());
        }

        if(response.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
            return "redirect:/archive/list";
        }
        return DEFAULT_VIEW;
    }

    @RequestMapping(value = URL_DISPLAY_ITEM, method = RequestMethod.POST)
    public String deleteItem(RedirectAttributes redirectAttributes,
                             @PathVariable(name = "id") String id,
                             @RequestParam(name = "btnDelete") String btnDelete) {

        // FIXME: add checking
        partService.delete(id);

        return "redirect:/archive/v1/list";
    };

    @RequestMapping(value = URL_CREATE_NEW, method = RequestMethod.GET)
    public String createNew(Model model) {
        if(!model.containsAttribute("item")) model.addAttribute("item", new HashMap<>());
        return DEFAULT_VIEW;
    }

    @RequestMapping(value = URL_CREATE_NEW, method = RequestMethod.POST)
    public String createNewSaving(RedirectAttributes redirectAttributes, @RequestParam Map<String, Object> params) {
        var item = extractItemValue(params);
        boolean isValid = validateItem(item);
        if(isValid) {
            redirectAttributes.addFlashAttribute("msg", "Элемент успешно добавлен");
            return "redirect:/archive/v1/list";
        }
        redirectAttributes.addFlashAttribute("msg", "Поля заполнены не верно");
        redirectAttributes.addFlashAttribute("item", item);
        return "redirect:/archive/v1/list/create-new";

    }

    private Map<String, Object> extractItemValue(Map<String, Object> params) {
        Map<String, Object> item = new HashMap<>();
        params.entrySet().forEach(entry -> {
            if(dtoKeys.contains(entry.getKey())) item.put(entry.getKey(), entry.getValue());
        });
        return item;
    }

    //FIXME: add logic
    private boolean validateItem(Map<String, Object> params) {

        boolean result = true;

        if(!params.containsKey("number") || !params.containsKey("name") || !params.containsKey("type")) result = false;

        return result;
    }

}

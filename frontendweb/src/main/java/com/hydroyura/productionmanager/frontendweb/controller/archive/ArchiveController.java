package com.hydroyura.productionmanager.frontendweb.controller.archive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.productionmanager.frontendweb.controller.AbstractController;
import com.hydroyura.productionmanager.frontendweb.rendered.RenderedFragment;
import com.hydroyura.productionmanager.frontendweb.rendered.RenderedFragmentType;
import com.hydroyura.productionmanager.frontendweb.service.IPartService;
import com.hydroyura.productionmanager.sharedapi.dto.DTOPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping(value = "/archive/v1")
public class ArchiveController extends AbstractController {

    static final String URL_DISPLAY_LIST = "/list";
    static final String URL_DISPLAY_ITEM = "/list/{id}";
    static final String URL_CREATE_NEW = "/list/create-new";
    static final String URL_DELETE = "/list/{id}/delete";
    static final String URL_UPDATE = "/list/{id}/update";
    static final String URL_SPECIFICATION = "/list/{id}/specification";

    @Autowired
    private IPartService partService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired @Qualifier(value = "DTOPartValidator")
    private Validator validator;


    @Override
    final protected void init2() {
        CONTROLLER_ID = "ARCHIVE_CONTROLLER_ID";
        DEFAULT_VIEW = "/archive/base-layout";

        //URLs.putIfAbsent(URL_DISPLAY_ITEM, URL_DISPLAY_ITEM);
        setFragmentToURL(URL_DISPLAY_LIST, new RenderedFragment().setType(RenderedFragmentType.BODY).setSource("/archive/fragments/fragments-archive.html").setName("archive-list"));
        setFragmentToURL(URL_DISPLAY_ITEM, new RenderedFragment().setType(RenderedFragmentType.BODY).setSource("/archive/fragments/fragments-archive-detail-item.html").setName("archive-detail-item"));
        setFragmentToURL(URL_CREATE_NEW, new RenderedFragment().setType(RenderedFragmentType.BODY).setSource("/archive/fragments/fragments-archive.html").setName("archive-create-new"));
        setFragmentToURL(URL_SPECIFICATION, new RenderedFragment().setType(RenderedFragmentType.BODY).setSource("/archive/fragments/fragments-archive-specification.html").setName("archive-specification"));

        // archive-create-new

        init1();

    }

    @RequestMapping(value = URL_DISPLAY_LIST, method = RequestMethod.GET)
    public String displayList(Model model, @ModelAttribute(name = "filter") ArchiveSearchFilter filter) {

        System.out.println("REQUEST");

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

    @RequestMapping(value = URL_DELETE, method = RequestMethod.GET)
    public String deleteItem(RedirectAttributes redirectAttributes,
                             @PathVariable(name = "id") String id) {
        // FIXME: add checking
        // add msg about successful deleting
        partService.delete(id);
        return "redirect:/archive/v1/list";
    };

    @RequestMapping(value = URL_CREATE_NEW, method = RequestMethod.GET)
    public String createNew(Model model) {
        if(!model.containsAttribute("item")) model.addAttribute("item", new DTOPart());
        return DEFAULT_VIEW;
    }

    @RequestMapping(value = URL_CREATE_NEW, method = RequestMethod.POST)
    public String createNewSaving(RedirectAttributes redirectAttributes, @ModelAttribute(name = "item") DTOPart item ) {
        DataBinder dataBinder = new DataBinder(item);
        dataBinder.setValidator(validator);
        dataBinder.validate();

        if(!dataBinder.getBindingResult().hasErrors()) {
            partService.create(item);
            redirectAttributes.addFlashAttribute("msg", "Элемент успешно добавлен");
            return "redirect:/archive/v1/list";
        }

        redirectAttributes.addFlashAttribute("msg", "Поля заполнены не верно");
        redirectAttributes.addFlashAttribute("item", item);

        return "redirect:/archive/v1/list/create-new";
    }

    @RequestMapping(value = URL_UPDATE, method = RequestMethod.POST)
    public String update(RedirectAttributes redirectAttributes,
                         @PathVariable(name = "id") String id,
                         @ModelAttribute DTOPart updatedPart) {
        updatedPart.setLastUpdate(LocalDate.now());
        partService.update(id, updatedPart);
        redirectAttributes.addFlashAttribute("msg", "Элемент успешно обновлен");
        return "redirect:/archive/v1/list/" + id;
    }

    @RequestMapping(value = URL_SPECIFICATION, method = RequestMethod.GET)
    public String specification(@PathVariable(name = "id") String id, Model model) {
        ResponseEntity<?> response = partService.getItemById(id);
        if(response.getStatusCode().equals(HttpStatus.OK)) {
            Map<String, String> item = (Map<String, String>) response.getBody();
            String type = (item.containsKey("type")) ? item.get("type") : null;

            if(type.equals("ASSEMBLY")) {
/*
                ResponseEntity<?> responseAssembly = rateService.getSpecificationByAssemblyId(id);
                if(responseAssembly.getStatusCode().equals(HttpStatus.OK)) {
                    model.addAttribute("specification", responseAssembly.getBody());
                }
                */
            } else {
                // redirect to list
            }
        }
        return DEFAULT_VIEW;
    }

}

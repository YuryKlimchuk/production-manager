package com.hydroyura.productionmanager.frontendweb.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

public interface IPartController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    String displayList(@RequestParam(name = "type", required = true, defaultValue = "PART") String type, Model model);

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    String displayItem(@PathVariable(name = "id") String id, Model model);

    @RequestMapping(value = "/list/{id}/specification", method = RequestMethod.GET)
    String displaySpecification(@PathVariable(name = "id") String id, Model model);

    @RequestMapping(value = "/list/items-to-add", method = RequestMethod.GET) @ResponseBody
    Collection<Map<String, Object>> getItems(@RequestParam(name = "type", required = true) String type);
}



//     PART, ASSEMBLY, STANDARD_PART, BUY_PART;
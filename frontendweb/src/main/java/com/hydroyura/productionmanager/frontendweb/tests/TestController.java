package com.hydroyura.productionmanager.frontendweb.tests;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class TestController {

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1(Model model) {

        Map<String, Object> map = Map.of("source", "fragments/default/base-layout-default.html", "name", "test1");



        model.addAttribute("bodyBlock", map);

        return "base-layout";
    }

}

package com.hydroyura.productionmanager.frontendweb.controller;

import com.hydroyura.productionmanager.frontendweb.rendered.RenderedFragment;
import com.hydroyura.productionmanager.frontendweb.rendered.RenderedFragmentProviderManager;
import com.hydroyura.productionmanager.frontendweb.rendered.RenderedFragmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

public abstract class AbstractController {

    protected String CONTROLLER_ID = "DEFAULT_ID";
    protected String DEFAULT_VIEW = "base-layout";
    protected String BASE_URL = "";


    protected Map<String, Map<RenderedFragmentType, RenderedFragment>> fragments = new HashMap<>();

    protected final Map<String, String>  URLs = new HashMap<>();
    protected List<RenderedFragment> beginScripts = new ArrayList<>();
    protected List<RenderedFragment> endScripts = new ArrayList<>();

     @PostConstruct
     final void init1() {
        BASE_URL = this.getClass().getAnnotation(RequestMapping.class).value()[0];

        renderedFragmentProviderManager.getJsForController(CONTROLLER_ID)
                .forEach(provider -> {
                    if(provider.getRenderedFragment().getType().equals(RenderedFragmentType.JS_BEGIN)) {
                        beginScripts.add(provider.getRenderedFragment());
                    }
                });

    }

    @Autowired
    private RenderedFragmentProviderManager renderedFragmentProviderManager;

    @PostConstruct
    protected abstract void init2();

    protected void setFragmentToURL(String URL, RenderedFragment fragment) {
        // check if URL exist
        if(!URLs.containsKey(URL)) {
            System.out.println("here will be logs");
            throw new RuntimeException("URL not exist!");
        }

        var subMap = fragments.getOrDefault(URL, new HashMap<RenderedFragmentType, RenderedFragment>());
        // before adding make checking
        subMap.put(fragment.getType(), fragment);
        fragments.putIfAbsent(URL, subMap);
    }

    @ModelAttribute(name = "bodyBlock")
    private RenderedFragment setBody(HttpServletRequest request) {
        String requestURL = null;
        try {
            requestURL = ((String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)).split(BASE_URL)[1];
        } catch (Exception ex) {
            // FIXME
            System.out.println("Here must be logs");
        }
        var fragment = fragments.getOrDefault(requestURL, Collections.emptyMap()).getOrDefault(RenderedFragmentType.BODY, null);
        return fragment;
    }

    @ModelAttribute(name = "beginScripts")
    public Collection<RenderedFragment> getBeginScripts() {
        return beginScripts;
    }

    @ModelAttribute(name = "endScripts")
    public Collection<RenderedFragment> getEndScripts() {
        return endScripts;
    }

}

package com.hydroyura.productionmanager.frontendweb.rendered.providers;

import com.hydroyura.productionmanager.frontendweb.rendered.RenderedFragment;
import com.hydroyura.productionmanager.frontendweb.rendered.RenderedFragmentProvider;
import com.hydroyura.productionmanager.frontendweb.rendered.RenderedFragmentType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class JSArchiveRenderedFragmentProvider implements RenderedFragmentProvider {

    private RenderedFragment fragment;
    private Collection<String> controllersId;


    public JSArchiveRenderedFragmentProvider() {
        fragment = new RenderedFragment()
                .setType(RenderedFragmentType.JS_BEGIN)
                .setName("archive.js")
                .setSource("/js/");

        controllersId = Arrays.asList("ARCHIVE_CONTROLLER_ID");
    }


    @Override
    public RenderedFragment getRenderedFragment() {
        return fragment;
    }

    @Override
    public Collection<String> getControllersIds() {
        return controllersId;
    }
}

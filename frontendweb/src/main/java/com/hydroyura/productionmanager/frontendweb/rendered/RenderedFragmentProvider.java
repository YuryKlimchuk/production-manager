package com.hydroyura.productionmanager.frontendweb.rendered;

import java.util.Collection;

public interface RenderedFragmentProvider {

    RenderedFragment getRenderedFragment();
    Collection<String> getControllersIds();

}

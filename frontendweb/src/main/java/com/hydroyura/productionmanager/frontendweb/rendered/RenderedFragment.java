package com.hydroyura.productionmanager.frontendweb.rendered;

public class RenderedFragment {

    private RenderedFragmentType type;
    private String source;
    private String name;


    public RenderedFragmentType getType() {
        return type;
    }

    public RenderedFragment setType(RenderedFragmentType type) {
        this.type = type;
        return this;
    }

    public String getSource() {
        return source;
    }

    public RenderedFragment setSource(String source) {
        this.source = source;
        return this;
    }

    public String getName() {
        return name;
    }

    public RenderedFragment setName(String name) {
        this.name = name;
        return this;
    }
}

package com.mark.wsp;

import java.util.List;

public class FilterResult<T> {
    private final Param param;
    private final List<T> results;

    public FilterResult(Param param, List<T> results) {
        this.param = param;
        this.results = results;
    }

    public Param getParam() {
        return param;
    }

    public List<T> getResults() {
        return results;
    }
}
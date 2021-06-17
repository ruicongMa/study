package com.mark.wsp;

import java.util.HashMap;
import java.util.Map;

public class ScorerContext {

    private ScorerConfiguration scorerConfiguration;


    private Map<String, Object> args = new HashMap<String, Object>();

    public ScorerContext(ScorerConfiguration scorerConfiguration) {
        this.scorerConfiguration = scorerConfiguration;
    }

    public Object get(String key) {
        return args.get(key);
    }

    public void addArgument(String key, Object object) {
        args.put(key, object);
    }

    public ScorerConfiguration getScorerConfiguration() {
        return scorerConfiguration;
    }

    public void setScorerConfiguration(ScorerConfiguration scorerConfiguration) {
        this.scorerConfiguration = scorerConfiguration;
    }

}
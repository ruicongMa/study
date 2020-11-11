package com.mark.wsp;

import java.util.List;

public class ScoreResult<T> {
    private final Param param;
    private final List<Score<T>> scores;

    public ScoreResult(Param param, List<Score<T>> scores) {
        this.param = param;
        this.scores = scores;
    }

    public Param getParam() {
        return param;
    }

    public List<Score<T>> getScores() {
        return scores;
    }

    @Override
    public String toString() {
        return "ScoreResult{" +
                "param=" + param +
                ", scores=" + scores +
                '}';
    }
}
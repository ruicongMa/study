package com.mark.wsp;

import java.util.Arrays;

public class Score<T> {

    private final T entity;
    private final Float[] scores;

    public Score(T entity, int size) {
        this.entity = entity;
        scores = new Float[size];
    }


    public T getEntity() {
        return entity;
    }

    public Float[] getScores() {
        return scores;
    }

    @Override
    public String toString() {
        return "Score{" +
                "entity=" + entity +
                ", scores=" + Arrays.toString(scores) +
                '}';
    }
}
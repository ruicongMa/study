package com.mark.wsp;

public class Tuple<K,V> {
    private final K k;
    private final V v;

    public Tuple(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K _1(){
        return k;
    }

    public V _2(){
        return v;
    }
}
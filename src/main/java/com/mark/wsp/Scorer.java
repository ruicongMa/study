package com.mark.wsp;

public interface Scorer<T>  {

    float score(T candidate, ScorerContext context, Param param) ;

    ScorerConfiguration parseConfiguration(ScorerInfo scorerInfo);
}
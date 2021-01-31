package com.ljunggren.rules.engine;

public interface Rule<I, O> {

    boolean applies(I input); 
    O execute(I input);
    
}

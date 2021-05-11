package com.ljunggren.rulesEngine;

public interface Rule<I, O> {

    boolean applies(I input); 
    O execute(I input);
    
}

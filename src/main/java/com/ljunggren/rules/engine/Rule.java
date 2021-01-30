package com.ljunggren.rules.engine;

public abstract class Rule<I, O> {

    public abstract boolean applies(I input); 
    public abstract O execute(I input);
    
}

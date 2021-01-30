package com.ljunggren.rules.engine;

import java.util.List;

public abstract class ConflictResolution<I, O> {

    public abstract O resolve(List<Rule<I, O>> rules, I input);
    
}

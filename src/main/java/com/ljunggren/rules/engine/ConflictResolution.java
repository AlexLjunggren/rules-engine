package com.ljunggren.rules.engine;

import java.util.List;

public interface ConflictResolution<I, O> {

    O resolve(List<Rule<I, O>> rules, I input);
    
}

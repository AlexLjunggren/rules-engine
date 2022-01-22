package io.ljunggren.rulesEngine;

import java.util.List;

public interface ConflictResolution<I, O> {

    Rule<I, O> resolve(List<Rule<I, O>> rules, I input);
    
}

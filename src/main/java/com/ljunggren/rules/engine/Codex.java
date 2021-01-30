package com.ljunggren.rules.engine;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Codex<I, O> {

    private List<Rule<I, O>> rules;
    private Rule<I, O> defaultRule;
    private ConflictResolution<I, O> conflictResolution;
    
    public Codex() {
        rules = new ArrayList<Rule<I,O>>();
    }
    
    public void add(Rule<I, O> rule) {
        rules.add(rule);
    }

    public void setConflictResolution(ConflictResolution<I, O> conflictResolution) {
        this.conflictResolution = conflictResolution;
    }

    public void setDefaultRule(Rule<I, O> defaultRule) {
        this.defaultRule = defaultRule;
    }

}

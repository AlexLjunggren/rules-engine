package com.ljunggren.rulesEngine;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class Engine<I, O> {

    private Codex<I, O> codex;
    private Consumer<Rule<I, O>> trace;

    public Engine(Codex<I, O> codex) {
        this.codex = codex == null ? new Codex<>() : codex;
    }
    
    public Engine<I, O> trace(Consumer<Rule<I, O>> trace) {
        this.trace = trace;
        return this;
    }
    
    public O execute(I input) {
        List<Rule<I, O>> rules = applicableRules(input);
        Rule<I, O> rule = rules.isEmpty() ? noRules(input) : executeRules(rules, input);
        trace(rule);
        return rule == null ? null : rule.execute(input);
    }
    
    private List<Rule<I, O>> applicableRules(I input) {
        return codex.getRules().stream()
                .filter(rule -> rule.applies(input))
                .collect(Collectors.toList());
    }
    
    private Rule<I, O> noRules(I input) {
        return codex.getDefaultRule() == null ? 
                null : codex.getDefaultRule();
    }
    
    private Rule<I, O> executeRules(List<Rule<I, O>> rules, I input) {
        return rules.size() == 1 ? 
                rules.get(0) : 
                    multipleRules(rules, input);
    }
    
    private Rule<I, O> multipleRules(List<Rule<I, O>> rules, I input) {
        return codex.getConflictResolution() == null ?
                rules.get(0) :
                    codex.getConflictResolution().resolve(rules, input);
    }
    
    private void trace(Rule<I, O> rule) {
        if (trace != null) {
            trace.accept(rule);
        }
    }
    
}

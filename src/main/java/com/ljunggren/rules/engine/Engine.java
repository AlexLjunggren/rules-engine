package com.ljunggren.rules.engine;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class Engine<I, O> {

    private Codex<I, O> codex;

    public Engine(Codex<I, O> codex) {
        this.codex = codex == null ? new Codex<>() : codex;
    }
    
    public O execute(I input) {
        List<Rule<I, O>> rules = applicableRules(input);
        return rules.isEmpty() ? noRules(input) : executeRules(rules, input);
    }
    
    private List<Rule<I, O>> applicableRules(I input) {
        return codex.getRules().stream()
                .filter(rule -> rule.applies(input))
                .collect(Collectors.toList());
    }
    
    private O noRules(I input) {
        return codex.getDefaultRule() == null ? null : 
            codex.getDefaultRule().execute(input);
    }
    
    private O executeRules(List<Rule<I, O>> rules, I input) {
        return rules.size() == 1 ? 
                rules.get(0).execute(input) :
                    multipleRules(rules, input);
    }
    
    private O multipleRules(List<Rule<I, O>> rules, I input) {
        return codex.getConflictResolution() == null ?
                rules.get(0).execute(input) :
                    codex.getConflictResolution().resolve(rules, input);
    }
    
}

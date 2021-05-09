package com.ljunggren.rules.engine.analyzer;

import com.ljunggren.rules.engine.Rule;
import com.ljunggren.rules.engine.annotation.RuleInfo;

public class Analyzer<I, O> {

    public String getName(Rule<I, O> rule) {
        if (rule == null) {
            return noRule();
        }
        RuleInfo ruleInfo = getRuleInfo(rule);
        return ruleInfo == null ? 
                rule.getClass().getSimpleName() :
                    ruleInfo.name();
    }
    
    public String getCondition(Rule<I, O> rule) {
        if (rule == null) {
            return noRule();
        }
        RuleInfo ruleInfo = getRuleInfo(rule);
        return ruleInfo == null ?
                noInfo() :
                    ruleInfo.condition();
    }
    
    public String getAction(Rule<I, O> rule) {
        if (rule == null) {
            return noRule();
        }
        RuleInfo ruleInfo = getRuleInfo(rule);
        return ruleInfo == null ?
                noInfo() :
                    ruleInfo.action();
    }
    
    private String noRule() {
        return "No rule found";
    }
    
    private String noInfo() {
        return "No info found";
    }
    
    private RuleInfo getRuleInfo(Rule<I, O> rule) {
        return rule.getClass().getAnnotation(RuleInfo.class);
    }
    
}

package com.ljunggren.rules.engine;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class CodexTest {

    @Test
    public void instantiationTest() {
        Codex<Object, Object> codex = new Codex<>();
        assertTrue(codex instanceof Codex);
        assertTrue(codex.getRules().isEmpty());
        assertNull(codex.getDefaultRule());
        assertNull(codex.getConflictResolution());
    }
    
    private Rule<Object, Object> rule = new Rule<Object, Object>() {
        public Object execute(Object input) {
            return input;
        }
        public boolean applies(Object input) {
            return true;
        }
    };
    
    @Test
    public void addTest() {
        Codex<Object, Object> codex = new Codex<>();
        codex.add(rule);
        assertTrue(codex.getRules().contains(rule));
    }
    
    @Test
    public void setConflictResoltutionTest() {
        Codex<Object, Object> codex = new Codex<>();
        ConflictResolution<Object, Object> conflictResolution = new ConflictResolution<Object, Object>() {
            public Object resolve(List<Rule<Object, Object>> rules, Object input) {
                return rules.get(0).execute(input);
            }
        };
        codex.setConflictResolution(conflictResolution);
        assertEquals(conflictResolution, codex.getConflictResolution());
    }
    
    @Test
    public void setDefaultRuleTest() {
        Codex<Object, Object> codex = new Codex<>();
        codex.setDefaultRule(rule);
        assertEquals(rule, codex.getDefaultRule());
    }

}
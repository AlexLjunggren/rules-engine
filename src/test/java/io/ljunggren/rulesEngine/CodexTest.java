package io.ljunggren.rulesEngine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
    public void addRuleTest() {
        Codex<Object, Object> codex = new Codex<>();
        codex.addRule(rule);
        assertTrue(codex.getRules().contains(rule));
    }
    
    @Test
    public void setConflictResoltutionTest() {
        Codex<Object, Object> codex = new Codex<>();
        ConflictResolution<Object, Object> conflictResolution = new ConflictResolution<Object, Object>() {
            public Rule<Object, Object> resolve(List<Rule<Object, Object>> rules, Object input) {
                return rules.get(0);
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

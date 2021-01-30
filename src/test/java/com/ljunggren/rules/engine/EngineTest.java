package com.ljunggren.rules.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EngineTest {
    
    private Codex<Object, String> codex;
    
    @Before
    public void setup() {
        codex = new Codex<Object, String>();
        codex.add(new Rule<Object, String>() {
            public boolean applies(Object input) {
                return input instanceof String;
            }
            public String execute(Object input) {
                return "String";
            }
        });
        codex.add(new Rule<Object, String>() {
            public boolean applies(Object input) {
                return input instanceof Number;
            }
            public String execute(Object input) {
                return "Number";
            }
        });
        codex.add(new Rule<Object, String>() {
            public boolean applies(Object input) {
                return input instanceof Integer;
            }
            public String execute(Object input) {
                return "Integer";
            }
        });
    }

    @Test
    public void executeNullCodexTest() {
        Engine<Object, String> engine = new Engine<>(null);
        assertNotNull(engine.getCodex());
        assertNull(engine.execute("Test"));
    }
    
    @Test
    public void executeTest() {
        Engine<Object, String> engine = new Engine<>(codex);
        assertEquals("String", engine.execute("Test"));
    }
    
    @Test
    public void executeMultipleResultTest() {
        Engine<Object, String> engine = new Engine<>(codex);
        assertEquals("Number", engine.execute(100));
    }

    @Test
    public void executeNoResultTest() {
        Engine<Object, String> engine = new Engine<>(codex);
        assertNull(engine.execute(true));
    }
    
    @Test
    public void executeWithDefaultRuleTest() {
        codex.setDefaultRule(new Rule<Object, String>() {
            public String execute(Object input) {
                return "Unknown";
            }
            public boolean applies(Object input) {
                return true;
            }
        });
        Engine<Object, String> engine = new Engine<>(codex);
        assertEquals("Unknown", engine.execute(true));
    }
    
    @Test
    public void executeWithConflictResolutionTest() {
        codex.setConflictResolution(new ConflictResolution<Object, String>() {
            public String resolve(List<Rule<Object, String>> rules, Object input) {
                return rules.get(1).execute(input);
            }
        });
        Engine<Object, String> engine = new Engine<>(codex);
        assertEquals("Integer", engine.execute(100));
    }
    
}

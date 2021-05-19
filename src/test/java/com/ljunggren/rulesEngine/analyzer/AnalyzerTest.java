package com.ljunggren.rulesEngine.analyzer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ljunggren.rulesEngine.Rule;
import com.ljunggren.rulesEngine.annotation.RuleInfo;

public class AnalyzerTest {
    
    private Analyzer<Object, String> analyzer = new Analyzer<>();
    private final String NO_RULE = "No rule found";
    private final String NO_INFO = "No info found";
    
    @RuleInfo(action = "Return the word String", condition = "If input is a String", name = "Is String")
    public class TestRuleWithAnnotation implements Rule<Object, String> {
        public boolean applies(Object input) {
            return input instanceof String;
        }
        public String execute(Object input) {
            return "String";
        }
    }
    
    public class TestRuleWithoutWithAnnotation implements Rule<Object, String> {
        public boolean applies(Object input) {
            return input instanceof String;
        }
        public String execute(Object input) {
            return "String";
        }
    }

    @Test
    public void instantiationTest() {
        assertTrue(analyzer instanceof Analyzer);
    }
    
    @Test
    public void actionTest() {
        assertEquals(analyzer.getAction(new TestRuleWithAnnotation()), "Return the word String");
    }
    
    @Test
    public void actionNoAnnotationTest() {
        assertEquals(analyzer.getAction(new TestRuleWithoutWithAnnotation()), NO_INFO);
    }
    
    @Test 
    public void actionNullTest() {
        assertEquals(analyzer.getAction(null), NO_RULE);
    }

    @Test
    public void conditionTest() {
        assertEquals(analyzer.getCondition(new TestRuleWithAnnotation()), "If input is a String");
    }
    
    @Test
    public void conditionNoAnnotationTest() {
        assertEquals(analyzer.getCondition(new TestRuleWithoutWithAnnotation()), NO_INFO);
    }
    
    @Test
    public void conditionNullTest() {
        assertEquals(analyzer.getCondition(null), NO_RULE);
    }

    @Test
    public void nameTest() {
        assertEquals(analyzer.getName(new TestRuleWithAnnotation()), "Is String");
    }
    
    @Test
    public void nameNoAnnotationTest() {
        assertEquals(analyzer.getName(new TestRuleWithoutWithAnnotation()), "TestRuleWithoutWithAnnotation");
    }
    
    @Test
    public void nameNullTest() {
        assertEquals(analyzer.getName(null), NO_RULE);
    }
    
}

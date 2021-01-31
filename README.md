## Rules Engine ###

Engine

```java
Engine<I, O> engine = new Engine<>(codex);
```

Codex - Set of rules

```java
Codex<I, O> codex = new Codex<>();
```

ConflictResolution - Interace for conflict resolution

```java
public interface ConflictResolution<I, O> {
    O resolve(List<Rule<I, O>> rules, I input);
}
```

Rule - Interface for rules

```java
public interface Rule<I, O> {
    boolean applies(I input); 
    O execute(I input);
}

```

## Codex ##

Codex contains three private members.

```java
    List<Rule<I, O>> rules;
    Rule<I, O> defaultRule;
    ConflictResolution<I, O> conflictResolution;
```

- rules - The set of rules that are iterated through by the Engine.
- defaultRule - Optional rule that will be triggered if no other rules are applicable.
- conflictResolution - Optional code triggered when multiple rules are applicable.


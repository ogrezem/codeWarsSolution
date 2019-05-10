package ru.ogrezem.codeWarsSolution.domain.games.mafia.config;

import java.util.Map;

import static java.util.Map.entry;

public class MafiaGameConfig {

    protected Map<String, String> speeches = Map.ofEntries (
            entry("", "")
    );

    protected Map<String, String> subjects = Map.ofEntries (
            entry("city", "город"),
            entry("commissioner", "мент"),
            entry("wholeMafia", "мафия"),
            entry("oneMafia", "мафиози"),
            entry("innocent", "ни в чём не повинный"),
            entry("citizen", "мирный житель"),
            entry("doctor", "доктор")
    );

    protected Map<String, String> objects = Map.ofEntries (
            entry("commissioner", "мента"),
            entry("wholeMafia", "мафия"),
            entry("oneMafia", "мафиози"),
            entry("innocent", "ни в чём не повинного"),
            entry("citizen", "мирного жителя"),
            entry("doctor", "доктора")
    );

    protected Map<String, String> actions = Map.ofEntries (
            entry("sleeping", "засыпает"),
            entry("wakingUp", "просыпается"),
            entry("treatment", "оказывает лечение"),
            entry("killing", "делает своё грязное дело"),
            entry("killingAsResult", "убила"),
            entry("arresting", "проводит арест"),
            entry("arrestingAsResult", "арестовал")
    );

    public Map<String, String> getSpeeches() {
        return speeches;
    }

    public Map<String, String> getSubjects() {
        return subjects;
    }

    public Map<String, String> getObjects() {
        return objects;
    }

    public Map<String, String> getActions() {
        return actions;
    }
}

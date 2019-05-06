package ru.ogrezem.codeWarsSolution.domain.jts;

public class TestAnswer {

    private long id;
    private String content;

    public TestAnswer(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}

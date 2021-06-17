package com.mark.wsp;

public class ScorerInfo {

    private int type;
    private String content;
    private int priority;

    public ScorerInfo(int type, String content, int priority) {
        this.type = type;
        this.content = content;
        this.priority = priority;
    }

    public ScorerInfo() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
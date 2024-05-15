package com.example.Practice14;

public class Level {
    private String complexity;
    private String levelName;

    public Level(String complexity, String levelName)
    {
        this.complexity = complexity;
        this.levelName = levelName;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelName() {
        return levelName;
    }

    @Override
    public boolean equals(Object obj) {
        return this.levelName.equals(((Level) obj).getLevelName()) &&
                this.complexity.equals(((Level) obj).getComplexity());
    }
}

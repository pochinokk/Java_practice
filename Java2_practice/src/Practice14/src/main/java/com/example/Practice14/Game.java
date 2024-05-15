package com.example.Practice14;


public class Game {

    private String name;
    private String creationDate;
    private Level level;

    public Game(String name, String creationDate, Level level){
        this.name = name;
        this.creationDate = creationDate;
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Game) obj).getName()) &&
                this.creationDate.equals(((Game) obj).getCreationDate());
    }
}

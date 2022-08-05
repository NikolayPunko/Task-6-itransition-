package ru.punko.spingcourse.Project2Boot.models;

public class Setting {

    private String region;

    private double mistakes;

    private int page;

    private long seed;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getMistakes() {
        return mistakes;
    }

    public void setMistakes(double mistakes) {
        this.mistakes = mistakes;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "region='" + region + '\'' +
                ", mistakes=" + mistakes +
                '}';
    }
}

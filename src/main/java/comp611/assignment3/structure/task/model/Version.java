package comp611.assignment3.structure.task.model;

public class Version {

    private static class VersionIncrement {
        private int current;

        private static VersionIncrement instance;

        private static VersionIncrement getInstance() {
            if (instance == null) {
                instance = new VersionIncrement();
            }
            return instance;
        }

        private int getNumber() {
            return current++;
        }

        private VersionIncrement() {
            this.current = 0;
        }
    }

    private final int number;

    public Version() {
        this.number = VersionIncrement.getInstance().getNumber();
    }

    public Version(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return number == version.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return "Version{number=" + number + "}";
    }
}

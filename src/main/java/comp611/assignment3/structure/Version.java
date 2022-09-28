package comp611.assignment3.structure;

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
}

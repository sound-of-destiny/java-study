package sping;

public class hello {
    private static hello ourInstance = new hello();

    public static hello getInstance() {
        return ourInstance;
    }

    private hello() {
    }
}

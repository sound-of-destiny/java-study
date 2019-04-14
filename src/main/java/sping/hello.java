package cn.edu.sdu.jt1078mediaserver;

public class hello {
    private static hello ourInstance = new hello();

    public static hello getInstance() {
        return ourInstance;
    }

    private hello() {
    }
}

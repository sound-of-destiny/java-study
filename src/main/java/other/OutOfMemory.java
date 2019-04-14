package other;

public class OutOfMemory {

    /**
     * 通过递归调用方法,不停的产生栈帧,一直把栈空间堆满,直到抛出异常 ：
     *
     * @param args
     */
    public static void main(String[] args) {
        OutOfMemory sof = new OutOfMemory();
        sof.stackOverFlowMethod();
    }

    public void stackOverFlowMethod() {
        stackOverFlowMethod();
    }
}

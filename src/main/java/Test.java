import org.springframework.cglib.proxy.Enhancer;

public class Test {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        Integer i = 128, j = 128;
        System.out.println(i == j);
        String string1 = "123456789";
        while (true) {
            String a = new String(string1);
        }
    }
}

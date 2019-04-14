package sping;

public class HelloWorld {

    private ByeService byeService;

    public ByeService getByeService() {
        return byeService;
    }

    public void setByeService(ByeService byeService) {
        this.byeService = byeService;
    }

    public void sayHello() {
        System.out.println("-----  hello  ----");
    }
}

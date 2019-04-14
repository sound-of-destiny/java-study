package sping;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanLifeCycleImpl implements BeanLifeCycle, BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private String company;

    public BeanLifeCycleImpl() {
        System.out.println("1, 实例化Bean");
    }

    public void setCompany(String company) {
        System.out.println("2, 设置属性");
        this.company = company;
    }


    @Override
    public void setBeanName(String name) {
        System.out.println("3, 设置Bean的name" + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("4, 执行ApplicationContextAware 在哪一个上下文");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("6, InitializingBean afterPropertiesSet");
    }

    public void myInit() {
        System.out.println("7, init-method");
    }

    public void get() {
        System.out.println("9, 自己的业务逻辑");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("10, DisposableBean destroy");
    }

    public void myDestroy() {
        System.out.println("11, destroy-method");
    }

}

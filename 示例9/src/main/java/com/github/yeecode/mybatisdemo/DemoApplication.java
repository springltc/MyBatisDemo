package com.github.yeecode.mybatisdemo;

import com.github.yeecode.mybatisdemo.model.ProxyHandler;
import com.github.yeecode.mybatisdemo.model.User;
import com.github.yeecode.mybatisdemo.model.UserInterface;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Proxy;

/**
 * 基于反射的动态代理示例。
 */
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws Exception {
        // 创建被代理对象
        User user = new User();
        // 初始化一个ProxyHandler对象
        ProxyHandler proxyHandler = new ProxyHandler(user);

        // 使用Proxy类的一个静态方法生成代理对象userProxy
        Object proxyInstance = Proxy.newProxyInstance(User.class.getClassLoader(), new Class[]{UserInterface.class}, proxyHandler);

        UserInterface userProxy = (UserInterface) proxyInstance;

        // 通过接口调用相应的方法，实际由Proxy执行
        userProxy.sayHello("易哥");
    }
}

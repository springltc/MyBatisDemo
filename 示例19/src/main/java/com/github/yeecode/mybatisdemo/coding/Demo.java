package com.github.yeecode.mybatisdemo.coding;

import com.github.yeecode.mybatisdemo.model.ProxyHandler;
import com.github.yeecode.mybatisdemo.model.User;
import org.springframework.cglib.proxy.Enhancer;


/**
 * @author liutc
 * @date 2024/4/25 16:37
 */
public class Demo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new ProxyHandler<>());
        enhancer.setSuperclass(User.class);
        User user = (User) enhancer.create();

        user.sayHello("hello world");
    }

}

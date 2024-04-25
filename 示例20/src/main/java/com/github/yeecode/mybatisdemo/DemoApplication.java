package com.github.yeecode.mybatisdemo;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Method;

/**
 * javassist的使用示例。
 * Javassist是一个用于在Java字节码级别上编辑、创建和操作Java类的库。它提供了一种简单而强大的方式来动态修改 Java 程序的运行时行为，
 * 比如动态生成类、添加字段、修改方法等。Javassist可以用于实现各种功能，比如AOP、动态代理、动态配置等。
 */
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        // 定义一个类
        CtClass userCtClazz = pool.makeClass("com.github.yeecode.mybatisdemo.User");
        // 创建name属性
        CtField nameField = new CtField(pool.get("java.lang.String"), "name", userCtClazz);
        userCtClazz.addField(nameField);
        // 创建name的setter
        CtMethod setMethod = CtNewMethod.make("public void setName(String name) { this.name = name;}", userCtClazz);
        userCtClazz.addMethod(setMethod);
        // 创建sayHello方法
        CtMethod sayHello = CtNewMethod.make("public String sayHello() { return \"Hello, I am \" + this.name ;}", userCtClazz);
        userCtClazz.addMethod(sayHello);

        Class<?> userClazz = userCtClazz.toClass();
        // 创建一个对象
        Object user = userClazz.newInstance();
        // 为对象设置name值
        Method[] methods = userClazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("setName")) {
                method.invoke(user, "易哥");
            }
        }
        // 调用对象sayHello方法
        for (Method method : methods) {
            if (method.getName().equals("sayHello")) {
                String result = (String) method.invoke(user);
                System.out.println(result);

            }
        }
    }
}

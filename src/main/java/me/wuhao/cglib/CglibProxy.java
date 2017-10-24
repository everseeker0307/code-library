package me.wuhao.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by everseeker on 16/7/26.
 */

/**
 * CGLIB代理：实现原理类似于JDK动态代理，只是它在运行期间生成的代理对象是针对目标类扩展的子类。
 * CGLIB是高效的代码生成包，底层是依靠ASM（开源的java字节码编辑类库）操作字节码实现的，性能比JDK强。
 * CGLIB是针对类来实现代理的，他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的方法进行代理。
 * CGLib采用非常底层的字节码技术，可以为一个类创建一个子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，并顺势植入横切逻辑。
 */
public class CglibProxy implements MethodInterceptor {
    //被代理对象
    Object targetObject;

    //动态生成一个新的类，使用父类的无参构造方法创建一个指定了特定回调的代理实例
    public Object getProxyObject(Object object) {
        this.targetObject = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(this);
        //设置生成类的父类类型. 使用Enhancer类，需要为其实例指定一个父类，也就是我们的目标对象。这样，我们新创建出来的对象就是目标对象的子类。
        enhancer.setSuperclass(targetObject.getClass());
        //动态生成字节码并返回代理对象
        return enhancer.create();
    }

    //拦截方法
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = null;

        //匹配正则表示式(以print开头)的方法, 在调用方法前后输出信息
        if (method.getName().matches("^print.*")) {
            System.out.println("proxy start......");
            result = methodProxy.invoke(targetObject, args);
            System.out.println("proxy end.");
        } else {
            result = methodProxy.invoke(targetObject, args);
        }

        return result;
    }
}

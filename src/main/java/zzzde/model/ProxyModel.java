package zzzde.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class ProxyModel {
    public static void main(String[] args) {
        //1.创建真实对象
        Lenovo lenovo = new Lenovo();

        //2.动态代理增强lenovo对象
        /*
            三个参数：
                1. 类加载器：真实对象.getClass().getClassLoader()
                2. 接口数组：真实对象.getClass().getInterfaces()
                3. 处理器：new InvocationHandler()，一般使用匿名内部类创建
         */
        SaleComputer proxy_lenovo = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {

            /*
                代理逻辑编写的方法：代理对象调用的所有方法都会触发invoke执行
                    参数：
                        1. proxy:代理对象
                        2. method：代理对象调用的方法，被封装为的对象
                        3. args:代理对象调用的方法时，传递的实际参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /*System.out.println("该方法执行了....");
                System.out.println(method.getName());
                System.out.println(args[0]);
*/
                //判断是否是sale方法，针对真实类不同的方法来设置不同的代理
                if (method.getName().equals("sale")) {
                    //1.增强参数
                    double money = (double) args[0];
                    money = money * 0.85;
                    System.out.println("专车接你....");
                    //使用真实对象调用该方法，获取返回值
                    String obj = (String) method.invoke(lenovo, money);//使用method.invoke()方法
                    System.out.println("免费送货...");
                    //2.增强返回值
                    return obj + "_鼠标垫";
                } else {//对于不需要增强的方法，直接使用method.invoke()传递给原对象的方法
                    Object obj = method.invoke(lenovo, args);
                    return obj;
                }
            }
        });

        //3.调用方法

       /* String computer = proxy_lenovo.sale(8000);
        System.out.println(computer);*/

        proxy_lenovo.show();//访问代理对象而不是原对象
    }
}

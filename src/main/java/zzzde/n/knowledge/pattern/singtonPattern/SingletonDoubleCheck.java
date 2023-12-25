package zzzde.n.knowledge.pattern.singtonPattern;

/**
 * 双重检验模式的单例 （初始化对象）
 * new SingletonDoubleCheck() 动作在jvm中基本上可以分为三步。
 * 1、为对象分配内存空间
 * 2、调用 Singleton 的构造函数来初始化成实例化对象
 * 3、将对象地址指向内存空间。
 * 在完成第三步的时候才能算非空
 * 故如果 Singleton实例在判断 == null 的时候 由于指令重排序问题，可能为 132 或者 123 。如果线程一 执行 132 。线程二执行 123 。
 * 并同时执行，线程一在执行完 13 之后，线程2 抢占了cpu执行权限，则判断为非空直接返回对象，其实根本没有这个对象，故直接报错。
 */

// DCL （DoubleCheckLock）
public class SingletonDoubleCheck {

    private SingletonDoubleCheck() {
    }

    private static volatile SingletonDoubleCheck singtonDoubleCheck = null; //为对象分配内存空间，volatile关键字防止指令重排序问题

    public static SingletonDoubleCheck getInstance() {
        if (singtonDoubleCheck == null) {
            synchronized (SingletonDoubleCheck.class) {
                if (singtonDoubleCheck == null) {
                    singtonDoubleCheck = new SingletonDoubleCheck();
                }
            }
        }
        return singtonDoubleCheck;
    }
}

// 一般不使用，每次获取都要经过一次同步，效率非常低。
class LazySingleton {
    private LazySingleton() {
    }

    private static LazySingleton singleton;

    public static synchronized LazySingleton getInstance() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }

}

class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton(); // 类初始化的时候就加载，比较费空间

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}

/**
 * 静态内部类实现单例模式
 * 第一次加载Singleton类时不会初始化instance，只有在第一次调用getInstance()方法时，虚拟机会加载SingletonHolder类，初始化instance。
 * 这方式既保证线程安全，单例对象的唯一，也延迟了单例的初始化，推荐使用这种方式来实现单例模式。
 */
class Singleton {
    private Singleton() {
    }
    public static Singleton getInstance() { // 第一次调用方法的时候，类加载去加载类，初始化instance，保证线程安全，并且对象唯一。
        return SingletonHolder.instance;
    }
    /**
     * 静态内部类
     */
    private static class SingletonHolder {
        private static Singleton instance = new Singleton();
    }
}

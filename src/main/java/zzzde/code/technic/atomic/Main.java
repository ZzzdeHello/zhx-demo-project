package zzzde.code.technic.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger() ;
        atomicInteger.set(0);

        Thread[] threads = new Thread[20];

        for (int i = 0 ; i <20 ;i++ ) {
            threads[i] = new Thread(new Runnable(){
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        atomicInteger.incrementAndGet();//自增1
                        System.out.println( Thread.currentThread().getName()+"线程+1;结果为：" + atomicInteger.get());
                    }
                }
            });
            threads[i].start();
        }
        //等待所有累加线程都结束
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(atomicInteger);
    }


    public static void main(String[] args) {
        ManualCallWorkOrder order = new ManualCallWorkOrder();order.setCustomerMobile("19124297237");order.setId(1L);
        ManualCallWorkOrder order1 = new ManualCallWorkOrder();order1.setCustomerMobile("15968867017");order1.setId(2L);
        ManualCallWorkOrder order2 = new ManualCallWorkOrder();order2.setCustomerMobile("15968867017");order2.setId(3L);
        List<ManualCallWorkOrder> manualCallWorkOrders = new ArrayList();
        manualCallWorkOrders.add(order);
        manualCallWorkOrders.add(order1);
        manualCallWorkOrders.add(order2);
        // 根据手机号码去重
        List<ManualCallWorkOrder> uniqueMobileOrderList = manualCallWorkOrders.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(ManualCallWorkOrder::getCustomerMobile))), ArrayList::new)
        );
        System.out.println(JSON.toJSONString(uniqueMobileOrderList));
    }
}

package zzzde.project.technic.annotation.demo;

import zzzde.project.technic.annotation.MyTag;

public class HomeActivity extends AppCompatActivity {

    @MyTag(name = "BMW",size = 100)
    Car car;

    @Override
    public void onCreate() {
        super.onCreate();
        //这里我们要首先注册一下这个类
        AnnotationCar.instance().inject(this);
        //当程序运行的时候这里将会输出该类Car的属性值。
        System.out.println(("Car is "+car.toString()));
    }


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
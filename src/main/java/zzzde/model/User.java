package zzzde.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

    /*** 姓名*/
    private String name;
    /*** 薪资*/
    private Integer salary;
    /*** 年龄*/
    private Integer age;
    /*** 性别*/
    private String sex;
    /*** 地区*/
    private String area;

    public User(String name, Integer salary, Integer age, String sex, String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }

    public User() {
    }
}

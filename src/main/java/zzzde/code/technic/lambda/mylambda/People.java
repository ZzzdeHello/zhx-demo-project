package zzzde.code.technic.lambda.mylambda;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class People {
    String name;
    Integer money;
    Integer sex;
    Integer age;
    Double score;
    Integer flag;
}

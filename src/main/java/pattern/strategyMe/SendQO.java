package pattern.strategyMe;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zzzde
 * @version 1.0
 * @date 2023/3/20 14:06
 */
@Data
@Accessors(chain = true)
public class SendQO {

    private String name;

    private String content;

    private Integer type;
}

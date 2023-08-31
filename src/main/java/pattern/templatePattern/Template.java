package pattern.templatePattern;

import java.util.List;

/**
 * @author zzzde
 * @version 1.0
 * @date 2023/4/1 15:33
 */
public class Template {

        protected interface MastSuppler{

            List<Mast> apply(int projectId);
        }

        protected interface Transform<T>{
            List<T> apply(List<Mast> masts);
        }

        protected interface PropertiesConsumer<T>{
            void apply(List<T> properties);
        }

        public <T> void template(int projectId, MastSuppler suppler, Transform<T> transform, PropertiesConsumer<T> consumer){
            System.out.println("projectId is " + projectId);
            //1.List<Mast> masts = step1(int projectId);
            List<Mast> masts = suppler.apply(projectId);
            //2.List<T> properties = step2(List<Mast> masts)
            List<T> properties = transform.apply(masts);

            //3.use or consume these properties（print to console ,save to datebase）

            consumer.apply(properties);
        }

}

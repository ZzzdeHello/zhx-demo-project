package lambda;

/**
 * lambda语法二：
 * 方法归属者::方法名 （静态方法的归属者为类名，普通方法归属者为对象）
 */
public class TestLambdaForMethod implements LambdaInterface {

        public static void main(String[] args) {
            //lambda1 语法一：利用参数a， 引用了已经实现的 doubleNum 方法
            ReturnOneParam lambda1 = a -> doubleNum(a);
            System.out.println(lambda1.method(3));

            //lambda2 语法二： 利用了方法归属者和方法名 引用了已经实现的 doubleNum 方法
            ReturnOneParam lambda2 = TestLambdaForMethod::doubleNum;
            System.out.println(lambda2.method(3));

            TestLambdaForMethod exe = new TestLambdaForMethod();

            //lambda4 引用了已经实现的 addTwo 方法
            ReturnOneParam lambda4 = exe::addTwo;
            System.out.println(lambda4.method(2));
        }
        /**
         * 要求（入参和出参都需要和接口lambdaInterface中的一一对应，否者使用失败）
         * 1.参数数量和类型要与接口中定义的一致
         * 2.返回值类型要与接口中定义的一致
         */
        public static int doubleNum(int a) {
            return a * 2;
        }

        public  int addTwo(int a) {
            return a + 2;
        }

}

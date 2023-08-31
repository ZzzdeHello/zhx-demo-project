package function.supplier;

import java.util.function.Supplier;

public class SupplierUseDemo {
    public static void main(String[] args) {
        // 标准供应类
        Supplier<ProductStandard> standardSupplier = ProductStandard::new;
        Boolean aBoolean = qualityTesting(standardSupplier, 1L, 1L);

    }

    // 质检工作流，只有产品ID 一般为抽象类，ProductStandard 的子类可以有很多不同的标准
    public static Boolean qualityTesting(Supplier<? extends ProductStandard> supplier, Long productId, Long peopleId) {
        // 根据ID获取产品
        Supplier<Product> productSupplier = () -> new Product(productId);
        // Supplier<Product> productSupplier2 = Product::new;
        Product product = productSupplier.get();
        return product.getName().equals(supplier.get().getStandardName());
    }


}

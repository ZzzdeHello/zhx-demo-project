package zzzde.code.technic.function.supplier;

import lombok.Data;

/**
 * @author zzzde
 * @version 1.0
 * @date 2023/4/17 13:51
 */
@Data
public class Product {
    public Long productId;
    private String name;
    private Integer price;

    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Product(Long productId) {
        this.productId = productId;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

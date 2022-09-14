import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author Mark
 * @date 2021/8/9 12:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer id;
    private BigDecimal price;
    private Integer stock;
    private String category;

    private List<Product> products() {
        return Lists.newArrayList(
                new Product(1, new BigDecimal("9.92"), 100, "E"),
                new Product(2, new BigDecimal("19.92"), 200, "E"),
                new Product(3, new BigDecimal("29.92"), 500, "E"),

                new Product(4, new BigDecimal("19.92"), 200, "C"),
                new Product(5, new BigDecimal("9.32"), 300, "C"),
                new Product(6, new BigDecimal("18.92"), 500, "C"),

                new Product(7, new BigDecimal("23.92"), 300, "M"),
                new Product(8, new BigDecimal("11.92"), 500, "M"),
                new Product(9, new BigDecimal("79.92"), 100, "M"));
    }

    public void test() {
        Flux.just(products()).subscribe(System.out::println);
    }


    public static void main(String[] args) {
        // Product product = new Product();
        // Flux.just(product.products()).collectMultimap();
    }
}

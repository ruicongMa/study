package cn.springboot.mark;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author Mark
 * @create 2020/3/7
 */
public class Test {
    public static void main(String[] args) {
        // Collection coll = new ArrayList();
        // coll.add("111");
        // coll.add(new Person(1L,"tom"));
        // coll.add(222);
        //
        // System.out.println(coll.contains(new Person(1L,"tom")));

        // HashMap<String,String> map = new HashMap<>();
        // String value = map.put("a", "aaa");
        // System.out.println(value);
        // String put = map.put("a", "bbb");
        // System.out.println(put);

        BigDecimal price = new BigDecimal(100);
        price = price.add(BigDecimal.valueOf(200));
        System.out.println(price);
    }
}

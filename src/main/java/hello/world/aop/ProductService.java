package hello.world.aop;

import jakarta.inject.Singleton;

import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class ProductService {

    private final ConcurrentHashMap<String, Product> productConcurrentHashMap= new ConcurrentHashMap<>();


    public void addProduct(Product product) {
        productConcurrentHashMap.put(product.getProductName(), product);
    }

    public void removeProduct(Product product) {
        productConcurrentHashMap.remove(product.getProductName());
    }
}

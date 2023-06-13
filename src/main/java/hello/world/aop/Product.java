package hello.world.aop;

import io.micronaut.context.annotation.Parameter;

import jakarta.annotation.PreDestroy;

import java.util.Objects;

@ProductBean // (1)
public class Product {
    private final String productName;
    private boolean active = false;

    public Product(@Parameter String productName) { // (2)
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @PreDestroy // (3)
    void disable() {
        active = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return active == product.active && Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, active);
    }
}

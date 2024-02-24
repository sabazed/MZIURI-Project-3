import java.util.Arrays;

public class GetProductsResponse {
    private String[] productNames;

    public String[] getProductNames() {
        return productNames;
    }

    public void setProductNames(String[] productNames) {
        this.productNames = productNames;
    }

    @Override
    public String toString() {
        return "GetProductsResponse{" +
                "productNames=" + Arrays.toString(productNames) +
                '}';
    }
}

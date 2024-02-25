import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/store/product")
public class ProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("name");


        if (productName == null || productName.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Product product = getProductByName(productName);


        if (product == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        GetProductInfoResponse getProductInfoResponse = new GetProductInfoResponse();
        getProductInfoResponse.setName(product.getName());
        getProductInfoResponse.setPrice(product.getPrice());
        getProductInfoResponse.setAmount(product.getAmount());

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        mapper.writeValue(response.getWriter(), getProductInfoResponse);
    }

    private Product getProductByName(String productName) {
        List<Product> products = DatabaseManager.getInstance().getProducts();
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PurchaseRequest purchaseRequest = mapper.readValue(request.getReader(), PurchaseRequest.class);


        Product product = getProductByName(purchaseRequest.getName());


        PurchaseResponse purchaseResponse = new PurchaseResponse();


        if (product != null && product.getAmount() >= purchaseRequest.getAmount()) {

            int remainingAmount = product.getAmount() - purchaseRequest.getAmount();
            product.setAmount(remainingAmount);
            DatabaseManager.getInstance().updateProduct(product);


            purchaseResponse.setName(product.getName());
            purchaseResponse.setRemainingAmount(remainingAmount);
        } else {

            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        mapper.writeValue(out, purchaseResponse);
    }



    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");


        if (!"asd".equals(password)) {

            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid password");
            return;
        }


        ObjectMapper mapper = new ObjectMapper();
        AddProductRequest addProductRequest = mapper.readValue(request.getReader(), AddProductRequest.class);


        String productName = addProductRequest.getName();
        Product product = getProductByName(productName);


        if (product == null) {
            // If the product is not found, send a 405 Method Not Allowed response
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Product not found");
            return;
        }


        int updatedAmount = product.getAmount() + addProductRequest.getAmount();
        product.setAmount(updatedAmount);


        DatabaseManager.getInstance().addProduct(product);


        AddProductResponse addProductResponse = new AddProductResponse();
        addProductResponse.setName(product.getName());
        addProductResponse.setRemainingAmount(product.getAmount());


        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        mapper.writeValue(out, addProductResponse);
    }

}

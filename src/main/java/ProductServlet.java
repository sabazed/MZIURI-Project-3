import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("name");

        if (productName == null || productName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product name parameter is missing");
            return;
        }


        Product product = DatabaseManager.getInstance().getProductByName(productName);

        if (product == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
            return;
        }


        GetProductInfoResponse getProductInfoResponse = new GetProductInfoResponse();
        getProductInfoResponse.setName(product.getName());
        getProductInfoResponse.setPrice(product.getPrice());
        getProductInfoResponse.setAmount(product.getAmount());

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, getProductInfoResponse);
    }



    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        AddProductRequest addProductRequest = mapper.readValue(request.getReader(), AddProductRequest.class);


        Product product = getProductByName(addProductRequest.getName());

        if (product == null) {

            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Product not found");
            return;
        }


        if (!addProductRequest.getPassword().equals(AddProductRequest.getPassword())) {

            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid password");
            return;
        }


        product.setAmount(product.getAmount() + addProductRequest.getAmount());


        AddProductResponse addProductResponse = new AddProductResponse();
        addProductResponse.setName(product.getName());
        addProductResponse.setRemainingAmount(product.getAmount());


        response.setContentType("application/json");
        mapper.writeValue(response.getWriter(), addProductResponse);
    }


}

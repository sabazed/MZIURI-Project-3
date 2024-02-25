import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class StoreServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        List<Product> products = DatabaseManager.getInstance().getProducts();

        String[] productNames = new String[products.size()];
        for (int i = 0; i < products.size(); i++) {
            productNames[i] = products.get(i).getName();
        }


        GetProductsResponse getProductsResponse = new GetProductsResponse();
        getProductsResponse.setProductNames(productNames);


        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, getProductsResponse);
    }
}


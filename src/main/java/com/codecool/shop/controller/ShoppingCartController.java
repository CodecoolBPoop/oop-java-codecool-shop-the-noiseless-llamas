package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/shopping-cart"})
public class ShoppingCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ShoppingCartDao shoppingCartsDataStore = ShoppingCartDaoMem.getInstance();


        String productId = request.getParameter("id");
        if (isValidProductId(productDataStore, productId)) {
            Product productToAdd = productDataStore.find(Integer.valueOf(productId));
            ShoppingCart cart = shoppingCartsDataStore.find(1);
            if (cart.contains(productToAdd)) cart.incrementQuantityById(Integer.valueOf(productId));
            else cart.addToCart(productToAdd);

            System.out.println(productDataStore.getAll().toString());
        }

        String decrementId = request.getParameter("decrement");
        if (isValidProductId(productDataStore, decrementId)) {
            Product productToDecrement = productDataStore.find(Integer.valueOf(decrementId));
            ShoppingCart cart = shoppingCartsDataStore.find(1);
            if (cart.contains(productToDecrement)) cart.decrementQuantityById(Integer.valueOf(decrementId));
            else cart.removeFromCart(productToDecrement);
        }



        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("recipient", "World");
        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        context.setVariable("cart", shoppingCartsDataStore.find(1));
        context.setVariable("currency", productDataStore.getAll().get(0).getDefaultCurrency()); //get's the currency of the first item in the cart and sets it as currency of the cart
        engine.process("shopping-cart.html", context, response.getWriter());

    }

    private static boolean isValidProductId(ProductDao dao, String id) {
        boolean valid = false;
        try {
            int intId = Integer.valueOf(id);
            for (Product product: dao.getAll()) {
                if (product.getId() == intId) valid = true;
                System.out.println("OOOOKAAAYY");
            }
        } catch (Exception e) {
            System.out.println("nope");
        }
        return valid;
    }
}

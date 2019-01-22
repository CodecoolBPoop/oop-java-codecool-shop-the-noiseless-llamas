package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/","/cart"})

public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ShoppingCartDao shoppingCartsDataStore = ShoppingCartDaoMem.getInstance();

        String productId = req.getParameter("id");
        if (isValidProductId(productDataStore, productId)) {
            Product productToAdd = productDataStore.find(Integer.valueOf(productId));
            ShoppingCart cart = shoppingCartsDataStore.find(1);
            if (cart.contains(productToAdd)) cart.incrementQuantityById(Integer.valueOf(productId));
            else cart.addToCart(productToAdd);

           // productToAdd.incrementQuantityInCartBy(1);
            //shoppingCartsDataStore.find(1).incrementNumberOfItems(1);
            System.out.println(productDataStore.getAll().toString());
        }
//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariables(params);
        context.setVariable("recipient", "World");
        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        context.setVariable("cart", shoppingCartsDataStore.find(1));
        engine.process("product/index.html", context, resp.getWriter());
    }

    private static boolean isValidProductId(ProductDao dao, String id) {
        boolean valid = false;
        try {
            int intId = Integer.valueOf(id);
            for (Product product: dao.getAll()) {
                if (product.getId() == intId) valid = true;
            }
        } catch (Exception e) {
            System.out.println("nope");
        }
        return valid;
    }

}

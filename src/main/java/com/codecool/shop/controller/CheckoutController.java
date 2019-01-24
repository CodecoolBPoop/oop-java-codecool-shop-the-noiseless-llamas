package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Order;
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
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    ProductDao productDataStore = ProductDaoMem.getInstance();
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    ShoppingCartDao shoppingCartsDataStore = ShoppingCartDaoMem.getInstance();
    OrderDao orderDataStore = OrderDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("cart", shoppingCartsDataStore.find(1));
        context.setVariable("currency", productDataStore.getAll().get(0).getDefaultCurrency());
        engine.process("checkout.html", context, response.getWriter()); //TODO: switch checkout.html to payment if payment is ready
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String, String> orderDetails = new HashMap<>();
        ShoppingCart cart = shoppingCartsDataStore.find(1);
        ArrayList<Product> orderedItems = cart.getProductList();
        float totalPrice = cart.getTotal();
        String paymentType = request.getParameter("paymentType");
        orderDetails.put("firstName", request.getParameter("firstName"));
        orderDetails.put("lastName", request.getParameter("lastName"));
        orderDetails.put("email", request.getParameter("email"));
        orderDetails.put("city", request.getParameter("city"));
        orderDetails.put("state", request.getParameter("state"));
        orderDetails.put("zip", request.getParameter("zip"));
        orderDetails.put("address", request.getParameter("address"));

        Order newOrder = new Order(orderDetails, totalPrice, paymentType, orderedItems);
        orderDataStore.add(newOrder);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("cart", shoppingCartsDataStore.find(1));
        context.setVariable("currency", productDataStore.getAll().get(0).getDefaultCurrency());
        context.setVariable("order", newOrder);

        engine.process("payment.html", context, response.getWriter());

    }
}

package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
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

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String categoryIdUrl =  req.getParameter("category_id");
        Integer categoryId = gettingProductCategoryFromUrl(productDataStore, categoryIdUrl);
//        context.setVariables(params);
        context.setVariable("recipient", "World");
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("category", productCategoryDataStore.find(categoryId));
        context.setVariable("selector", categoryId);
        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(categoryId)));
        engine.process("product/index.html", context, resp.getWriter());
    }

    public int gettingProductCategoryFromUrl(ProductDao productCategoryDataStore, String categoryIdUrl) {
        Integer categoryId;
        try {
            categoryId = Integer.valueOf(categoryIdUrl);
            if (categoryId > productCategoryDataStore.getAll().size() || categoryId < 0 ) {
                categoryId = 1;
            }
        }
        catch (Exception e) {
            categoryId = 1;
        }
        return categoryId;
    }

}

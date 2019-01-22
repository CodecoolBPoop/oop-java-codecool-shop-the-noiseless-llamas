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
        String supplierIdUrl =  req.getParameter("supplier_id");
        Integer categoryId = getIntegerFromUrlParam(productCategoryDataStore.getAll().size(), categoryIdUrl);
        Integer supplierId = getIntegerFromUrlParam(productDataStore.getAll().size(), categoryIdUrl);

//        context.setVariables(params);
        context.setVariable("recipient", "World");
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("category", productCategoryDataStore.find(categoryId));
        context.setVariable("selector", categoryId);
        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(categoryId)));
        engine.process("product/index.html", context, resp.getWriter());
    }

    public int getIntegerFromUrlParam(int max, String categoryIdUrl) {
        Integer categoryId;
        try {
            categoryId = Integer.valueOf(categoryIdUrl);
            if (categoryId > max || categoryId < 0 ) {
                categoryId = 1;
            }
        }
        catch (Exception e) {
            categoryId = 1;
        }
        return categoryId;
    }

}

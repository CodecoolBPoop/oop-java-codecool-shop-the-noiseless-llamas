package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.config.TemplateEngineUtil;
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
import java.lang.reflect.Array;
import java.util.Arrays;

@WebServlet(urlPatterns = {"/", "cart", "cartDecrement"})

public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductDao productDataStoreJDBC = new ProductDaoJDBC();
        System.out.println(productDataStoreJDBC.getAll().toString());
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ShoppingCartDao shoppingCartsDataStore = ShoppingCartDaoMem.getInstance();
        StringBuffer currentUrl = req.getRequestURL();
        //currentUrl.delete(0, 14);
        System.out.println(currentUrl);

       String productId = req.getParameter("cart_id");
        if (isValidProductId(productDataStore, productId)) {
            Product productToAdd = productDataStore.find(Integer.valueOf(productId));
            ShoppingCart cart = shoppingCartsDataStore.find(1);
            if (cart.contains(productToAdd)) cart.incrementQuantityById(Integer.valueOf(productId));
            else cart.addToCart(productToAdd);

           // productToAdd.incrementQuantityInCartBy(1);
            //shoppingCartsDataStore.find(1).incrementNumberOfItems(1);
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String categoryIdUrl =  req.getParameter("category_id");
        String supplierIdUrl =  req.getParameter("supplier_id");
        Integer categoryId = getIntegerFromUrlParam(productCategoryDataStore.getAll().size(), categoryIdUrl);
        Integer supplierId = getIntegerFromUrlParam(supplierDataStore.getAll().size(), supplierIdUrl);
        context.setVariable("recipient", "World");
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("category", productCategoryDataStore.find(categoryId));
        context.setVariable("category_selector", categoryId);
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("supplier_selector", supplierId);
        System.out.println(productCategoryDataStore.find(categoryId) + "dasgfgfdsgf");
        System.out.println(supplierDataStore.find(supplierId)+ "dasgfdgdfg");
        System.out.println(Arrays.toString(((ProductDaoJDBC) productDataStoreJDBC).getData().toArray()));
        context.setVariable("products", (productDataStoreJDBC.getBy(productCategoryDataStore.find(categoryId), supplierDataStore.find(supplierId))));
        context.setVariable("cart", shoppingCartsDataStore.find(1));

        System.out.println(productDataStore.getAll().toString());
        System.out.println(supplierDataStore.getAll().toString());
        System.out.println(productCategoryDataStore.getAll().toString());


//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        engine.process("product/index.html", context, resp.getWriter());
//        context.setVariables(params);
    }


    public int getIntegerFromUrlParam(int max, String urlId) {
        Integer id;
        try {
            id = Integer.valueOf(urlId);
            if (id > max || id < 0 ) {
                id = 0;
            }
        }
        catch (Exception e) {
            id = 1;
        }
        return id;
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
        }
        return valid;
    }

}

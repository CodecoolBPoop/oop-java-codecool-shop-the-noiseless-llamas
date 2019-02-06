package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();

        //setting up a new supplier
        Supplier allSupplier = new Supplier("All", "All items.");
        supplierDataStore.add(allSupplier);
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier mentors = new Supplier("Mentors", "Mentorship is a relationship in which a more experienced or more knowledgeable person helps to guide a less experienced or less knowledgeable person.");
        supplierDataStore.add(mentors);

        //setting up a new product category
        ProductCategory allCategory = new ProductCategory("All", "All", "All items.");
        productCategoryDataStore.add(allCategory);
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "A mobile phone that has a touchscreen interface, Internet access, and an operating system capable of running downloaded apps.");
        productCategoryDataStore.add(phone);
        ProductCategory softSkill = new ProductCategory("Soft Skill", "HR", "Soft skills are a combination of people skills, social skills, communication skills, character or personality traits, attitudes, career attribute, social intelligence and emotional intelligence quotients among others that enable people to navigate their environment, work well with others, perform well, and achieve their goals");
        productCategoryDataStore.add(softSkill);
        ProductCategory hardSkill = new ProductCategory("Hard Skill", "Mentoring", "Hard skills, also called technical skills, are any skills relating to a specific task or situation. It involves both understanding and proficiency in such specific activity that involves methods, processes, procedures, or techniques");
        productCategoryDataStore.add(hardSkill);
        ProductCategory hardware = new ProductCategory("Hardware", "IT", "Computer hardware includes the physical, tangible parts or components of a computer, such as the cabinet, central processing unit, monitor, keyboard, computer data storage, graphic card, sound card, speakers and motherboard");
        productCategoryDataStore.add(hardware);
        ProductCategory consultation = new ProductCategory("Consultation", "Mentoring", "A meeting with an expert, such as a medical doctor, in order to seek advice.");
        productCategoryDataStore.add(consultation);
        ProductCategory workshop = new ProductCategory("Workshop", "Mentoring", "a meeting at which a group of people engage in intensive discussion and activity on a particular subject or project.");
        productCategoryDataStore.add(workshop);



        //setting up products and printing it
       /* productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Lenovo 8", 80, "USD", "Lenovo's latest smartphone", phone, lenovo));
        productDataStore.add(new Product("Feedback", 15, "USD", "Information about reactions to a product, a person's performance of a task, etc. which is used as a basis for improvement.", softSkill,mentors));
        productDataStore.add(new Product("Feed-forward", 30, "USD", "Feedforward is a method of teaching and learning that illustrates or indicates a desired future behavior or path to a goal. Feedforward provides information, images, etc. exclusively about what one could do right in the future", softSkill,mentors));
        productDataStore.add(new Product("Python", 5000, "USD", "Python is an interpreted, high-level, general-purpose programming language. Created by Guido van Rossum and first released in 1991", hardSkill,mentors));
        productDataStore.add(new Product("Git", 500, "USD", "Git is a distributed version control system for tracking changes in source code during software development", hardSkill,mentors));
        productDataStore.add(new Product("OOP", 99999, "USD", "Object-oriented programming (OOP) is a programming paradigm based on the concept of \"objects\", which may contain data, in the form of fields, often known as attributes; and code, in the form of procedures, often known as methods", hardSkill,mentors));
        productDataStore.add(new Product("Pa Practice", 100, "USD", "Practice or practise is the act of rehearsing a behavior over and over, or engaging in an activity again and again, for the purpose of improving or mastering it, as in the phrase \"practise makes perfect\".", consultation,mentors));
        productDataStore.add(new Product("Spirit Lifting", 0, "USD", "http://inspirobot.me", consultation,mentors));
        productDataStore.add(new Product("Lean Poker", 200, "USD", "We don't know, 'cause it got postponed :(", workshop,mentors));
        productDataStore.add(new Product("Active Listening", 10, "USD", "I wasn't paying attention", workshop,mentors));
        productDataStore.add(new Product("JavaScript", 7500, "USD", "Javascript is a high-level, interpreted programming language that conforms to the ECMAScript specification. It is a language that is also characterized as dynamic, weakly typed, prototype-based and multi-paradigm.", hardSkill,mentors));
        productDataStore.add(new Product("UML", 250, "USD", "UML (Unified Modeling Language) is a standard language for specifying, visualizing, constructing, and documenting the artifacts of software systems. UML was created by the Object Management Group (OMG)", hardSkill,mentors));
*/

        //setting up shopping carts
        ShoppingCart cart = new ShoppingCart("default", "default");

        shoppingCartDataStore.add(cart);
    }
}

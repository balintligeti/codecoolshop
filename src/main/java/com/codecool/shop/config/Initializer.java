package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
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
                //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier apple = new Supplier("Apple","Computers and smartphones");
        supplierDataStore.add(apple);
        Supplier samsung = new Supplier("Samsung","Tv's and smartphones");
        supplierDataStore.add(samsung);
        Supplier playstation = new Supplier("PlayStation","Gaming consoles");
        supplierDataStore.add(playstation);
        Supplier kodak = new Supplier("Kodak","Camera");
        supplierDataStore.add(kodak);
        Supplier olympus = new Supplier("Olympus","Camera");
        supplierDataStore.add(olympus);


        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);

        ProductCategory camera = new ProductCategory("Camera","Hardware","Cameras are great.");
        productCategoryDataStore.add(camera);

        ProductCategory smartphone = new ProductCategory("Smartphone","Hardware","A phone, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(smartphone);

        ProductCategory computers = new ProductCategory("Computers","Hardware","Computers are great.");
        productCategoryDataStore.add(computers);

        ProductCategory tv = new ProductCategory("Smart TV","Hardware","Explore a collection of TVs inspired by how we live, designed to enhance " +
                "what we watch and the spaces we watch in.");
        productCategoryDataStore.add(tv);

        ProductCategory console = new ProductCategory("Consoles","Hardware", "Consoles are great");
        productCategoryDataStore.add(console);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Apple Iphone X",900,"USD","This product is not Apple certified, but has been professionally inspected and tested by Amazon-qualified suppliers.",smartphone,apple));
        productDataStore.add(new Product("Apple Iphone XS",495,"USD","This product is not Apple certified, but has been professionally inspected and tested by Amazon-qualified suppliers.",smartphone,apple));
        productDataStore.add(new Product("Apple iMac",2299,"USD","T3.8GHz 8-Core Processor with Turbo Boost up to 5.0GHz 512GB Storage Retina 5K Display",computers,apple));
        productDataStore.add(new Product("55\" Class Q90T QLED 4K UHD HDR Smart TV",1699,"USD","See every detail at any angle. Engineered to reduce glare and enhance color, providing a vibrant picture no matter where you sitSamsung's powerful 4K Quantum processor automatically optimizes the picture to deliver a more immersive experience",tv,samsung));
        productDataStore.add(new Product("Kodak PIXPRO Astro Zoom",129,"USD","Kodak PIXPRO Astro Zoom AZ252-WH 16MP Digital Camera with 25X Optical Zoom and 3\" LCD (White)",camera,kodak));
        productDataStore.add(new Product("Kodak Mini Shot 3",100,"USD","Kodak Mini Shot 3 Retro Camera - Portable Instant Camera and Photo Printer – 2-in-1 Printer Compatible with iOS & Android – Bluetooth Connection – 3x3-inch Real Photo Printer- 4PASS Technology",camera,kodak));
        productDataStore.add(new Product("Olympus OM-D E-M1",500,"USD","Olympus OM-D E-M1 Mark III Black Camera Body",camera,olympus));
        productDataStore.add(new Product("Samsung Galaxy S20",1000,"USD","Samsung Galaxy S20 5G Factory Unlocked New Android Cell Phone US Version",smartphone,samsung));
        productDataStore.add(new Product("Apple Mac mini",799,"USD","3.6GHz quad-core 8th-generation Intel Core i3 processor8GB 2666MHz DDR4 memoryIntel UHD Graphics 630256GB PCIe-based SSD storage¹",computers,apple));
        productDataStore.add(new Product("95\" Class Q900 QLED Smart 8K UHD TV (2019)",59999,"USD","Samsung QLED 8K Q900 delivers the super big screen clarity you've been waiting for. With fewer visible pixels, enjoy the feeling of pure immersion, even up close. See the bigger picture. Screen sizes up to 98\" Samsung offers a range of larger than life 75\"+ class TV's that take on-screen entertainment up a level. ",tv,samsung));
        productDataStore.add(new Product("85\" Class Q950TS QLED 8K UHD HDR Smart TV (2020)",12999,"USD","Take your viewing experience to the next level with a nearly-invisible frame.See the mind-blowing sharpness and depth of real 8K with 16 times more resolution than HDTV. Feel the power of every frame with 33 million pixels at ultimate clarity.",tv,samsung));
        productDataStore.add(new Product("iBUYPOWER Gaming PC",999,"USD"," System: Intel Core i7-9700F 8-Core 3. 0GHz (4. 70 GHz Max Turbo) | 16GB DDR4-2666 RAM | 1TB HDD | 240GB SSD | Genuine Windows 10 Home 64-bitGraphics: NVIDIA GeForce GTX 1660 Ti 6GB Dedicated Gaming Video Card | VR Ready | 1x DVI | 1x HDMI | 1x Display PortConnectivity: 4 x USB 3. 0 | 2 x USB 2. 0 | 1x RJ-45 Network Ethernet 10/100/1000 | Audio: 7. 1 ChannelSpecial Add-Ons: Tempered Glass RGB Gaming Case | 802. 11AC Wi-Fi Included | 16 Color RGB Lighting Case | Free Gaming Keyboard & RGB Gaming Mouse | No Bloatware: 1 Year Parts & Labor + Free Lifetime US Tech Support | Assembled in the U. S. A ",computers,amazon));
        productDataStore.add(new Product("PlayStation 5",1500,"USD","Experience lightning-fast loading with an ultra-high speed SSD, deeper immersion with support for haptic feedback, adaptive triggers and 3D Audio, and an all-new generation of incredible PlayStation® games.",console,playstation));



    }


}

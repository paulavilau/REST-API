package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.example.demo.entity.Category;
import com.example.demo.entity.Subcategory;
import com.example.demo.entity.Product;
import com.example.demo.entity.Supplier;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;


@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    
    private String theAllowedOrigins = "http://localhost:19000";

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.PUT};

        config.exposeIdsFor(Category.class);

        config.exposeIdsFor(Subcategory.class);

        config.exposeIdsFor(Product.class);

        config.exposeIdsFor(Supplier.class);

        config.exposeIdsFor(Cart.class);

        config.exposeIdsFor(CartItem.class);

        
        disableHttpMethods(Category.class, config, theUnsupportedActions);

        disableHttpMethods(Subcategory.class, config, theUnsupportedActions);

        disableHttpMethods(Product.class, config, theUnsupportedActions);

        disableHttpMethods(Supplier.class, config, theUnsupportedActions);


        /*Configure CORS Mapping */
        cors.addMapping(config.getBasePath() + "/**")
            .allowedOrigins(theAllowedOrigins);
    }

     private void disableHttpMethods(Class theClass,
                                    RepositoryRestConfiguration config,
                                    HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions));
    }
    }

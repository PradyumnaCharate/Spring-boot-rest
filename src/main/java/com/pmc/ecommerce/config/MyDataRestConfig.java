package com.pmc.ecommerce.config;

import com.pmc.ecommerce.entity.Country;
import com.pmc.ecommerce.entity.Product;
import com.pmc.ecommerce.entity.ProductCategory;
import javax.persistence.metamodel.EntityType;

import com.pmc.ecommerce.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


//spring boot do not automatically expose entity ids to rest api so configuring it here using JPA entity manager
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    //The @Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished.
    // The @Autowired annotation can be used to autowire bean on the setter method just like
    // @Required annotation, constructor, a property or
    // methods with arbitrary names and/or multiple arguments.
    //What is difference between @bean and @autowire?
    //@Bean is just for the metadata definition to create the bean(equivalent to tag).
    // @Autowired is to inject the dependancy into a bean(
    private EntityManager entityManager;
    @Autowired
    //this is class constructor
    public MyDataRestConfig(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }



    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};

        // disable HTTP methods for ProductCategory: PUT, POST and DELETE
        disableHttpMethods(Product.class, config, theUnsupportedActions);
        disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);
        disableHttpMethods(Country.class, config, theUnsupportedActions);
        disableHttpMethods(State.class, config, theUnsupportedActions);

        // call an internal helper method
        exposeIds(config);

        //configure cors mapping
        
    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }
    //object ids are not shown by default so oveeriding that behaviour
    private void exposeIds(RepositoryRestConfiguration config){
        //expose entity ids

        //get list of all enity classes from entity manager
        Set<EntityType<?>> entities=entityManager.getMetamodel().getEntities();


        //create an array of entity types
        List<Class> entityClasses=new ArrayList<>();

        //get entity types array
        for (EntityType tempEntityType : entities){
            entityClasses.add(tempEntityType.getJavaType());
        }

        //expose entity ids for given entity types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);

        config.exposeIdsFor(domainTypes);
    }
}
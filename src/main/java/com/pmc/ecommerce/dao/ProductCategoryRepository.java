package com.pmc.ecommerce.dao;

import com.pmc.ecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
//1st argument is entity type and second is primary key type
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}

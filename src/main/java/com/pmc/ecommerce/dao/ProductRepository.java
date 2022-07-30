package com.pmc.ecommerce.dao;

import com.pmc.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Adding automatic query methods provided by spring boot
    //because it starts from findBy it will be query method.
    //behind the scenes it will execute Select * from product where categoryId="id"
    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
    //select * from product p where p.name LIKE concat('%' ,:name,'%')
    Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
}

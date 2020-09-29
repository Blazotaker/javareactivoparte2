package com.example.productapifunctional.repository;

import com.example.productapifunctional.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository <Product, String>{

}

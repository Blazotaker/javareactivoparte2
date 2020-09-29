package com.example.productapifunctional.handler;

import com.example.productapifunctional.model.Product;
import com.example.productapifunctional.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

    private final ProductRepository repository;

    @Autowired
    public ProductHandler(ProductRepository repository){
        this.repository = repository;
    }

    public Mono<ServerResponse> getAllProducts(ServerRequest request){
        Flux<Product> products = repository.findAll();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(products, Product.class);
    }

    public Mono<ServerResponse>  getProduct(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Product> productMono = repository.findById(id);
        Mono<ServerResponse> notFound =  ServerResponse.notFound().build();

        return productMono
                .flatMap(product ->
                    ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                        .body(product, Product.class))
                                        .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> saveProduct (ServerRequest request){
        Mono<Product> productMono = request.bodyToMono(Product.class);

        return productMono.flatMap(product ->
                ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(repository.save(product),Product.class));
    }

   /* public Mono<ServerResponse> updateProduct(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Product> existingProductMono = repository.findById(id);
        Mono<Product> productMono = request.bodyToMono(Product.class);

        return productMono.zipWith(existingProductMono,
                (product, existingProductMono) ->
                        new Product(existingProductMono.getId(), product.getName(), product.getPrice())
                )
    }*/

}

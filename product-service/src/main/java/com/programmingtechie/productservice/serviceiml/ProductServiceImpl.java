package com.programmingtechie.productservice.serviceiml;

import com.programmingtechie.productservice.dto.ProductRequest;
import com.programmingtechie.productservice.model.Product;
import com.programmingtechie.productservice.model.ProductResponse;
import com.programmingtechie.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product productBuild = Product.builder().name(productRequest.getName()).description(productRequest.getDescription()).price(productRequest.getPrice()).build();
        productRepository.save(productBuild);
        log.info("Product {} is saved ",productBuild.getId());
    }

    public List < ProductResponse > getAllProducts() {
        List < Product > all = productRepository.findAll();
        List < ProductResponse > collect = all.stream().map(this::mapToProductResponse).collect(Collectors.toList());
        return collect;
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}

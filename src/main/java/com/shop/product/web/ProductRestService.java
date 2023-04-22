package com.shop.product.web;

import com.shop.product.Product;
import com.shop.product.ProductCommand;
import com.shop.product.ProductQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/products")
public class ProductRestService {

    private final ProductQuery productQuery;

    private final ProductCommand productCommand;

    private final PagedResourcesAssembler<Product> pagedResourcesAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> create(@Valid @RequestBody Product product){
        return ResponseEntity.ok(productCommand.create(product));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<EntityModel<Product>> findAll(@SortDefault(sort = "createdAt", direction = DESC) Pageable pageable){
        return pagedResourcesAssembler.toModel(productQuery.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable int id){
        productCommand.delete(id);
        return ResponseEntity.noContent().build();
    }

}

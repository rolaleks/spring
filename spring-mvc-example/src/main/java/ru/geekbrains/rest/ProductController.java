package ru.geekbrains.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.enity.Product;
import ru.geekbrains.persist.repl.ProductMapper;
import ru.geekbrains.persist.repl.ProductRepl;
import ru.geekbrains.persist.service.ProductService;

import java.util.List;

@RequestMapping("/api/v1/product")
@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setStudentsService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<ProductRepl> getAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}/id")
    public ProductRepl getById(@PathVariable Long id) {
        return productService.findReplById(id).orElseThrow(NotFoundException::new);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductRepl createProduct(@RequestBody Product product) {

        productService.save(product);

        return ProductMapper.MAPPER.fromProduct(product);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductRepl updateProduct(@RequestBody Product product) {

        if (product.getId() == null) {
            throw new NotFoundException("ID must not be null");
        }
        productService.save(product);

        return ProductMapper.MAPPER.fromProduct(product);
    }

    @DeleteMapping("/{id}/id")
    public void deleteStudent(@PathVariable Long id) {
        productService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(NotFoundException e) {

        ProductErrorResponse response = new ProductErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

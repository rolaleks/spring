package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persist.enity.Product;
import ru.geekbrains.persist.repo.ProductRepository;

@RequestMapping("/product")
@Controller
public class ProductsController {


    private ProductRepository productRepository;

    @Autowired
    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String products(Model model) {

        model.addAttribute("products", productRepository.findAll());
        return "product/products";
    }

    @GetMapping("create")
    public String create(Model model) {

        model.addAttribute("product", new Product());
        return "product/form";
    }

    @GetMapping("update/{id}")
    public String update(Model model, @PathVariable String  id) {

        model.addAttribute("product", productRepository.findById(Long.parseLong(id)));
        return "product/form";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable String  id) {

        productRepository.delete(Long.valueOf(id));
        return "redirect:/product";
    }

    @PostMapping
    public String save(Product product) {

        if (product.getId() == null) {
            productRepository.save(product);
        } else {
            productRepository.update(product);
        }
        return "redirect:/product";
    }
}

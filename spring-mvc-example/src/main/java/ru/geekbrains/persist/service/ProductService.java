package ru.geekbrains.persist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.enity.Product;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.persist.service.interdafaces.ProductServerInterface;
import ru.geekbrains.search.ProductSearch;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServerInterface {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void save(Product product) {
        repository.save(product);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(long id) {
        return repository.findById(id);
    }

    @Transactional
    public void update(Product product) {
        repository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(findById(id).get());
    }

    @Override
    public Page<Product> findAll(ProductSearch search) {

        Pageable pageable = search.getPageable();
        if (search.getMinCost() != null && search.getMaxCost() != null) {
            return repository.findByCostBetween(search.getMinCost(), search.getMaxCost(), pageable);
        } else if (search.getMinCost() != null) {
            return repository.findByCostGreaterThanEqual(search.getMinCost(), pageable);
        } else if (search.getMaxCost() != null) {
            return repository.findByCostLessThanEqual(search.getMaxCost(), pageable);
        }

        return repository.findAll(pageable);
    }

}

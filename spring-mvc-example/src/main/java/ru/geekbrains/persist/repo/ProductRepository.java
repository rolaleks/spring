package ru.geekbrains.persist.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.enity.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCostBetween(BigDecimal min, BigDecimal max, Pageable pageable);

    Page<Product> findByCostGreaterThanEqual(BigDecimal cost, Pageable pageable);

    Page<Product> findByCostLessThanEqual(BigDecimal cost, Pageable pageable);
}

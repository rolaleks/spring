package ru.geekbrains.search;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public class ProductSearch {

    private BigDecimal minCost;

    private BigDecimal maxCost;

    private Integer page = 1;

    private Integer pageSize = 5;

    private Integer totalPages;

    public ProductSearch() {
    }

    public ProductSearch(BigDecimal minCost, BigDecimal maxCost) {
        this.minCost = minCost;
        this.maxCost = maxCost;
    }

    public BigDecimal getMinCost() {
        return minCost;
    }

    public void setMinCost(BigDecimal minCost) {
        this.minCost = minCost;
    }

    public BigDecimal getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(BigDecimal maxCost) {
        this.maxCost = maxCost;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page != null ? page : this.page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize != null ? pageSize : this.pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Pageable getPageable() {
        return PageRequest.of(getPage() - 1, getPageSize());
    }
}

package com.bootstrap.api.service

import org.springframework.stereotype.Service

@Service
class ProductCatalogService(private val productRepository: ProductRepository) : ProductCatalog {
    override fun allProducts(): Iterable<Product> = productRepository.findAll()
    override fun productById(productId: Long): Product = productRepository.findById(productId).orElse(Product())
}
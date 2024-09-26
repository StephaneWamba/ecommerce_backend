package com.efrei.ecommerce.service;

import com.efrei.ecommerce.model.Purchase;
import com.efrei.ecommerce.model.Product;
import com.efrei.ecommerce.model.User;
import com.efrei.ecommerce.repository.PurchaseRepository;
import com.efrei.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> recommendProducts(User user) {
        List<Purchase> purchases = purchaseRepository.findByUser(user);
        Map<Long, Integer> productFrequency = new HashMap<>();

        // Count frequency of each product in user purchases
        for (Purchase purchase : purchases) {
            for (Product product : purchase.getProducts()) {
                productFrequency.put(product.getId(), productFrequency.getOrDefault(product.getId(), 0) + 1);
            }
        }

        // Sort products by frequency
        List<Long> sortedProductIds = new ArrayList<>(productFrequency.keySet());
        sortedProductIds.sort((id1, id2) -> productFrequency.get(id2) - productFrequency.get(id1));

        // Fetch top products from the repository and limit to top 5 recommendations
        List<Product> recommendedProducts = new ArrayList<>();
        for (Long productId : sortedProductIds) {
            if (recommendedProducts.size() >= 5) break;
            productRepository.findById(productId).ifPresent(recommendedProducts::add);
        }

        return recommendedProducts;
    }
}
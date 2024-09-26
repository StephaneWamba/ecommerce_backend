package com.efrei.ecommerce.controller;

import com.efrei.ecommerce.model.Product;
import com.efrei.ecommerce.model.User;
import com.efrei.ecommerce.repository.UserRepository;
import com.efrei.ecommerce.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Product>> getRecommendations(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Product> recommendations = recommendationService.recommendProducts(user);
        return ResponseEntity.ok(recommendations);
    }
}
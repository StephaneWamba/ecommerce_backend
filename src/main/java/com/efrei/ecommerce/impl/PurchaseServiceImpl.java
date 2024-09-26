package com.efrei.ecommerce.impl;

import com.efrei.ecommerce.model.Purchase;
import com.efrei.ecommerce.repository.PurchaseRepository;
import com.efrei.ecommerce.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase getPurchaseById(Long id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        return purchase.orElse(null);
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase updatePurchaseStatus(Long id, String status) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);
        if (optionalPurchase.isPresent()) {
            Purchase purchase = optionalPurchase.get();
            purchase.setStatus(status);
            return purchaseRepository.save(purchase);
        }
        return null;
    }

    @Override
    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }
}
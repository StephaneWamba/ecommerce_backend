package com.efrei.ecommerce.service;

import com.efrei.ecommerce.model.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> getAllPurchases();
    Purchase getPurchaseById(Long id);
    Purchase savePurchase(Purchase order);
    Purchase updatePurchaseStatus(Long id, String status);
    void deletePurchase(Long id);
}
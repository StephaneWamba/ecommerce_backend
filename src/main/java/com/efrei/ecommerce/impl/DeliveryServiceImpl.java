package com.efrei.ecommerce.service;

import com.efrei.ecommerce.model.Delivery;
import com.efrei.ecommerce.repository.DeliveryRepository;
import com.efrei.ecommerce.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery getDeliveryById(Long id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        return delivery.orElse(null);
    }

    @Override
    public Delivery saveDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery updateDeliveryStatus(Long id, String status) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(id);
        if (optionalDelivery.isPresent()) {
            Delivery delivery = optionalDelivery.get();
            delivery.setStatus(status);
            return deliveryRepository.save(delivery);
        }
        return null;
    }

    @Override
    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
}
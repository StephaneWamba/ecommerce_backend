package com.efrei.ecommerce;

import com.efrei.ecommerce.controller.DeliveryController;
import com.efrei.ecommerce.model.Delivery;
import com.efrei.ecommerce.service.DeliveryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeliveryControllerTest {

    @Mock
    private DeliveryService deliveryService;

    @InjectMocks
    private DeliveryController deliveryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllDeliveries() {
        List<Delivery> deliveries = Arrays.asList(new Delivery(), new Delivery());
        when(deliveryService.getAllDeliveries()).thenReturn(deliveries);

        List<Delivery> result = deliveryController.getAllDeliveries();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetDeliveryById() {
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        when(deliveryService.getDeliveryById(1L)).thenReturn(delivery);

        ResponseEntity<Delivery> result = deliveryController.getDeliveryById(1L);
        assertEquals(1L, result.getBody().getId());
    }

    @Test
    public void testSaveDelivery() {
        Delivery delivery = new Delivery();
        when(deliveryService.saveDelivery(delivery)).thenReturn(delivery);

        Delivery result = deliveryController.saveDelivery(delivery);
        assertEquals(delivery, result);
    }

    @Test
    public void testUpdateDeliveryStatus() {
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        delivery.setStatus("DELIVERED");
        when(deliveryService.updateDeliveryStatus(1L, "DELIVERED")).thenReturn(delivery);

        ResponseEntity<Delivery> result = deliveryController.updateDeliveryStatus(1L, "DELIVERED");
        assertEquals("DELIVERED", result.getBody().getStatus());
    }

    @Test
    public void testDeleteDelivery() {
        doNothing().when(deliveryService).deleteDelivery(1L);

        ResponseEntity<Void> result = deliveryController.deleteDelivery(1L);
        assertEquals(204, result.getStatusCodeValue());
    }
}
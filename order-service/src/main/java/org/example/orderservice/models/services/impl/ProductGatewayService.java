package org.example.orderservice.models.services.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.example.orderservice.clients.ProductClient;
import org.example.orderservice.exceptions.ProductNotFoundException;
import org.example.orderservice.exceptions.ProductServiceException;
import org.example.orderservice.models.dto.responses.ProductResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductGatewayService {

    private final ProductClient productClient;

    public ProductResponse getProductById(Long productId) {
        try {
            ProductResponse product = productClient.getProductById(productId);
            if (product == null) {
                throw new ProductNotFoundException(productId);
            }
            return product;
        } catch (FeignException.NotFound e) {
            throw new ProductNotFoundException(productId);
        } catch (FeignException e) {
            if (e.status() == 404) {
                throw new ProductNotFoundException(productId);
            }
            throw new ProductServiceException("Product service call failed", e);
        } catch (ProductNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ProductServiceException("Product service call failed", e);
        }
    }

}

package com.assessment.product_catalog_api.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.assessment.product_catalog_api.dto.ProducerDto;
import com.assessment.product_catalog_api.dto.ProductDto;
import com.assessment.product_catalog_api.dto.ProductRequest;
import com.assessment.product_catalog_api.entity.Producer;
import com.assessment.product_catalog_api.entity.Product;
import com.assessment.product_catalog_api.entity.ProductAttribute;
import com.assessment.product_catalog_api.repository.ProducerRepository;
import com.assessment.product_catalog_api.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAllWithProducerAndAttributes()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ProductDto createProduct(ProductRequest request) {
        Producer producer = producerRepository.findById(request.getProducerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not found"));

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .producer(producer)
                .build();

        applyAttributesFromRequest(product, request.getAttributes());

        Product saved = productRepository.save(product);
        return mapToDto(saved);
    }

    public ProductDto updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        Producer producer = producerRepository.findById(request.getProducerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setProducer(producer);

        if (product.getAttributes() != null) {
            product.getAttributes().clear();
        }

        applyAttributesFromRequest(product, request.getAttributes());

        Product saved = productRepository.save(product);
        return mapToDto(saved);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        productRepository.deleteById(id);
    }

    private void applyAttributesFromRequest(Product product, Map<String, String> attributes) {
        if (attributes == null || attributes.isEmpty()) {
            return;
        }

        if (product.getAttributes() == null) {
            product.setAttributes(new java.util.ArrayList<>());
        }

        attributes.forEach((name, value) -> {
            ProductAttribute attribute = ProductAttribute.builder()
                    .product(product)
                    .attributeName(name)
                    .attributeValue(value)
                    .build();
            product.getAttributes().add(attribute);
        });
    }

    private ProductDto mapToDto(Product product) {
        Map<String, String> attributesMap = product.getAttributes() == null
                ? Map.of()
                : product.getAttributes().stream()
                        .collect(Collectors.toMap(
                                ProductAttribute::getAttributeName,
                                ProductAttribute::getAttributeValue
                        ));

        ProducerDto producerDto = ProducerDto.builder()
                .id(product.getProducer().getId())
                .name(product.getProducer().getName())
                .build();

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .producer(producerDto)
                .attributes(attributesMap)
                .build();
    }
}


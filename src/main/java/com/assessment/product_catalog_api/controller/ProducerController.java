package com.assessment.product_catalog_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.product_catalog_api.dto.ProducerDto;
import com.assessment.product_catalog_api.service.ProducerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/producers")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping
    public ResponseEntity<List<ProducerDto>> getAllProducers() {
        return ResponseEntity.ok(producerService.getAllProducers());
    }

    @PostMapping
    public ResponseEntity<ProducerDto> createProducer(@RequestBody ProducerDto producerDto) {
        ProducerDto created = producerService.createProducer(producerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}


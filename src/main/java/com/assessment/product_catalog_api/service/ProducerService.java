package com.assessment.product_catalog_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.assessment.product_catalog_api.dto.ProducerDto;
import com.assessment.product_catalog_api.entity.Producer;
import com.assessment.product_catalog_api.repository.ProducerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final ProducerRepository producerRepository;

    public ProducerDto createProducer(ProducerDto dto) {
        Producer producer = Producer.builder()
                .name(dto.getName())
                .build();

        Producer saved = producerRepository.save(producer);
        return mapToDto(saved);
    }

    public List<ProducerDto> getAllProducers() {
        return producerRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ProducerDto mapToDto(Producer producer) {
        if (producer == null) {
            return null;
        }
        return ProducerDto.builder()
                .id(producer.getId())
                .name(producer.getName())
                .build();
    }
}


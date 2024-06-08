package com.example.skiresortapi.service;

import com.example.skiresortapi.entity.Area;
import com.example.skiresortapi.exception.ResourceNotFoundException;
import com.example.skiresortapi.repository.AreaRepository;
import org.springframework.stereotype.Service;

@Service
public class AreaServicnImpl {
    private final AreaRepository areaRepository;

    public AreaServicnImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public Area findByName(String name) {
        return areaRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Area not found"));
    }
}

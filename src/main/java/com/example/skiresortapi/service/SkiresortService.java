package com.example.skiresortapi.service;

import com.example.skiresortapi.controller.form.SkiresortCreateForm;
import com.example.skiresortapi.entity.Skiresort;

import java.util.List;

public interface SkiresortService {
    List<Skiresort> findAll();

    Skiresort findById(int id);

    Skiresort createSkiresort(SkiresortCreateForm skiresortCreateForm);
}

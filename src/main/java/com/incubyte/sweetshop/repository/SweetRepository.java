package com.incubyte.sweetshop.repository;

import com.incubyte.sweetshop.model.Sweet;

import java.util.List;

public interface SweetRepository {
    void add(Sweet sweet);
    boolean deleteById(int id);
    Sweet findByName(String name);
    Sweet findById(int id);
    List<Sweet> findAll();
}

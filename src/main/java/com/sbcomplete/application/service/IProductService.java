package com.sbcomplete.application.service;

import com.sbcomplete.application.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IProductService {
    List<Product> findPaginated(int pageNo, int pageSize, Optional<String> name);
    List<Product> findbyAvail(String availability);
}

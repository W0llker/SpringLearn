package org.example.JDBS.service;

import org.example.JDBS.DAO.CustomersDao;
import org.example.JDBS.Entity.Customers;
import org.example.JDBS.dto.CustomersDto;

import java.util.List;
import java.util.logging.Logger;

public class CustomerService {
    private static final CustomerService INSTANCE = new CustomerService();
    private final CustomersDao customersDao = CustomersDao.getInstance();

    public List<CustomersDto> findAll() {
        return customersDao.getAll().stream().map(customers ->
                        new CustomersDto(customers.getId(),
                                "%s - %s".formatted(
                                        customers.getFirstName(),
                                        customers.getEmail()))).toList();
    }

    public CustomersDto findById(Long id) {

        var customers = customersDao.getById(id).orElseThrow();
        return new CustomersDto(customers.getId(), customers.getCity());
    }

    public static CustomerService getInstance() {
        return INSTANCE;
    }

    private CustomerService() {
    }
}

package com.example.Practice23.service;


import com.example.Practice23.entity.Customer;
import com.example.Practice23.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;
    public void create(String username, String password, String roles) {
        Customer customer = Customer.builder()
                .name(username)
                .password(password)
                .roles(roles)
                .build();
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }
    public boolean exists(String username) {
        List<Customer> customers = customerRepository.findAll();
        for (Customer cust : customers){
            if (cust.getName().equals(username)){
                return true;
            }
        }
        return false;
    }


    public Long getIDByName(String username) {
        List<Customer> customers = customerRepository.findAll();
        for (Customer cust : customers){
            if (cust.getName().equals(username)){
                return cust.getId();
            }
        }
        return (long) -1;
    }
    public List<Customer> readAll() {
        return customerRepository.findAll();
    }
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    public void changePassword(String username, String newPassword) {
        List<Customer> customers = customerRepository.findAll();
        Customer customer = null;
        for (Customer cust : customers){
            if (cust.getName().equals(username)){
                customer = cust;
                break;
            }
        }
        if (customer != null) {
            System.out.println(customer.getName());
            customer.setPassword(passwordEncoder.encode(newPassword));
            customerRepository.save(customer);
        } else {
            System.out.println("Пользователь не найден");
        }
    }
}
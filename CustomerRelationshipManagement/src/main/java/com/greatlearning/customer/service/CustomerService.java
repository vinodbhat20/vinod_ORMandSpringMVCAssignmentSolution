package com.greatlearning.customer.service;

import java.util.List;

import com.greatlearning.customer.entity.Customer;

public interface CustomerService {
	
	public List<Customer> findAll();

	public Customer findCustomer(int id);

	public void save(Customer customer);

	public void deleteCustomer(int id);



}

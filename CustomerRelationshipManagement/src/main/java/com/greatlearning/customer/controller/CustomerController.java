package com.greatlearning.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.customer.entity.Customer;
import com.greatlearning.customer.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {


	@Autowired
	private CustomerService customerService;

	@RequestMapping("/customerList")
	public String customerForm(Model theModel) {

		List<Customer> customers = customerService.findAll();

		theModel.addAttribute("customers", customers);

		return "CustomerHome";
	}

	@RequestMapping("/addCustomer")
	public String addCustomer(Model theModel) {

		// create model attribute to bind form data
		Customer customer = new Customer();

		theModel.addAttribute("customer", customer);

		return "CustomerForm";
	}

	@RequestMapping("/updateCustomer")
	public String updateCustomer(@RequestParam("id") int id, Model theModel) {

		// create model attribute to bind form data
		Customer customer = customerService.findCustomer(id);

		theModel.addAttribute("customer", customer);

		return "CustomerForm";
	}

	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id,@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email) {

		Customer customer = null;

		if (email != null) {
			customer = customerService.findCustomer(id);
		}

		if (null == customer) {
			customer = new Customer(firstName, lastName, email);
		}
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		// save the Book
		customerService.save(customer);

		// use a redirect to prevent duplicate submissions
		return "redirect:/customer/customerList";

	}

	@RequestMapping("/deleteCustomer")
	public String delete(@RequestParam("id") int id) {
		if (id >0) {
			customerService.deleteCustomer(id);
		}
		// redirect to /Books/list
		return "redirect:/customer/customerList";

	}

}

package durgaJewellers.durgaJewellers.service;

import durgaJewellers.durgaJewellers.model.Customer.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    List<Customer> getAllCustomersByField(String name, String mobile, Long io);

    List<Customer> getAllCustomersByMobile(String mobile);

    Customer createCustomer(Customer customer);

    Customer patchCustomerItem(Customer customer);
}

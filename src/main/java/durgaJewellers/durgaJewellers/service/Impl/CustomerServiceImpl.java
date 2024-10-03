package durgaJewellers.durgaJewellers.service.Impl;

import durgaJewellers.durgaJewellers.dao.CustomerDao;
import durgaJewellers.durgaJewellers.model.Customer.Customer;
import durgaJewellers.durgaJewellers.model.Customer.CustomerItem;
import durgaJewellers.durgaJewellers.service.CustomerService;
import durgaJewellers.durgaJewellers.utils.CustomerUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @NonNull
    private final CustomerDao customerDao;

    @Autowired
    @NonNull
    CustomerUtils customerUtils;

    public List<Customer> getAllCustomers(){
        return customerDao.getAllCustomers();
    }

    public List<Customer> getAllCustomersByField(String name, String mobile, Long id){
        return customerDao.getAllCustomersByField(name, mobile, id);
    }

    public List<Customer> getAllCustomersByMobile(String mobile){
        return customerDao.getAllCustomersByMobile(mobile);
    }
    public Customer createCustomer(Customer customer){
        List<CustomerItem> customerList = customer.getCustomerItem();
        Customer finalCustomer = customer;
        customerList.forEach(item-> {
            item.setDate(LocalDate.now());
            item.setCustomer(finalCustomer);
        });
        customer = customerUtils.getTotalPrice(customer);
        return customerDao.save(customer);
    }

    public Customer patchCustomerItem(@NonNull Customer customer){
        Customer customerDetails = customerDao.findById(customer.getId());
        Map<Long, CustomerItem> existingItemMap = customerDetails.getCustomerItem().stream()
                .collect(Collectors.toMap(CustomerItem::getId, item -> item));

        for (CustomerItem newItem : customer.getCustomerItem()) {
            CustomerItem existingItem = existingItemMap.get(newItem.getId());

            if (existingItem != null) {
                // Update existing item
                existingItem.setCategory(newItem.getCategory());
                existingItem.setStatus(newItem.getStatus());
                existingItem.setJewellery(newItem.getJewellery());
                existingItem.setWeight(newItem.getWeight());
                existingItem.setLoanAmount(newItem.getLoanAmount());
                existingItem.setReceivedAmount(newItem.getReceivedAmount());
                existingItem.setInterest(newItem.getInterest());
                //TODO:- Decide whether current date or retain the original date
                existingItem.setDate(LocalDate.now());
            } else {
                // Add new item
                newItem.setCustomer(customerDetails);
                newItem.setDate(LocalDate.now());
                customerDetails.getCustomerItem().add(newItem);
            }
        }
        customerDetails = customerUtils.getTotalPrice(customerDetails);
        return customerDao.save(customerDetails);
    }
}

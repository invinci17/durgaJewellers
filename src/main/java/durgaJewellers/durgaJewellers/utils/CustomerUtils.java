package durgaJewellers.durgaJewellers.utils;

import durgaJewellers.durgaJewellers.model.Customer.Customer;
import durgaJewellers.durgaJewellers.model.Customer.CustomerItem;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerUtils {

    public Customer getTotalPrice(Customer customer){
        Long totalPrice = customer.getCustomerItem()
                .stream()
                .mapToLong(CustomerItem::getLoanAmount)
                .sum();
        customer.setTotalLoanPrice(totalPrice);
        return customer;

    }
}

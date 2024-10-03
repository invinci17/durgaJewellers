package durgaJewellers.durgaJewellers.dao;

import durgaJewellers.durgaJewellers.model.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.customerItem WHERE c.id = ?1")
    Customer findById(Long id);

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.customerItem")
    List<Customer> getAllCustomers();

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.customerItem WHERE LOWER(c.customerName) LIKE LOWER(CONCAT('%', :name, '%')) OR c.mobile = :mobile OR c.id = :id")
    List<Customer> getAllCustomersByField(String name, String mobile, Long id);

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.customerItem WHERE c.mobile = ?1")
    List<Customer> getAllCustomersByMobile(String mobile);
}

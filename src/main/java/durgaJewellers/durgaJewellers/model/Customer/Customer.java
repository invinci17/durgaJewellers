package durgaJewellers.durgaJewellers.model.Customer;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    private String mobile;

    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerItem> customerItem;

    private String comments;

    @Column(name = "total_loan_price", columnDefinition = "BIGINT")
    private Long totalLoanPrice = 0L;
}

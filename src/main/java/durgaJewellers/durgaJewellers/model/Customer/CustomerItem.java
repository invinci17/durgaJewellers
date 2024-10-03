package durgaJewellers.durgaJewellers.model.Customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import durgaJewellers.durgaJewellers.constants.ModelConstants;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class CustomerItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status = ModelConstants.OPEN;

    private String category;

    private String jewellery;

    private Double weight;

    @Column(name = "loan_amount")
    private Long loanAmount;

    @Column(name = "received_amount", columnDefinition = "BIGINT")
    private Long receivedAmount = 0L;

    private String comments;

    private Double interest;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore  // This prevents recursive serialization of Customer -> CustomerItem -> Customer
    private Customer customer;
}

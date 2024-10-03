package durgaJewellers.durgaJewellers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import durgaJewellers.durgaJewellers.model.Customer.Customer;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    List<Customer> response;
    Customer customer;
    CustomError error;


    public Response(){

    }
    public Response(List<Customer> l){
        this.response = l;
    }

    public Response(Customer c){
        this.customer = c;
    }

    public Response(CustomError customError){this.error = customError;}
}

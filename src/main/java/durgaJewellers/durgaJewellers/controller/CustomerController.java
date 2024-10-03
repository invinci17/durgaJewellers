package durgaJewellers.durgaJewellers.controller;

import durgaJewellers.durgaJewellers.constants.ErrorConstants;
import durgaJewellers.durgaJewellers.dto.CustomError;
import durgaJewellers.durgaJewellers.dto.Response;
import durgaJewellers.durgaJewellers.model.Customer.Customer;
import durgaJewellers.durgaJewellers.service.CustomerService;
import durgaJewellers.durgaJewellers.service.UserDetailsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    @NonNull private final CustomerService customerService;
    @NotNull private final UserDetailsService userDetailsService;

    @GetMapping("fetch/all")
    public ResponseEntity<Response> getAllCustomers(
            @RequestHeader("username") String username,
            @RequestHeader("password") String password) {

        log.info("Request for get all customers");
        if(userDetailsService.validateUser(username,password)) {
            List<Customer> customers = customerService.getAllCustomers();
            Response response = new Response(customers);
            log.info("Fetched {} customers", customers.size());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            log.info("Request failed due to wrong username or password");
            Response response = new Response(new CustomError(ErrorConstants.VALIDATION_FAILED));
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
        }
    }

    @GetMapping("fetch")
    public ResponseEntity<Response> getAllCustomersField(@Param(value = "name") String name,
                                                          @Param(value = "mobile") String mobile,
                                                          @Param(value = "id") Long id,
                                                          @RequestHeader("username") String username,
                                                          @RequestHeader("password") String password) {

        log.info("Request for get all customers by name");
        if(userDetailsService.validateUser(username,password)) {
            List<Customer> customers = customerService.getAllCustomersByField(name, mobile, id);
            Response response = new Response(customers);
            log.info("Fetched {} customers by name", customers.size());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            log.info("Request failed due to wrong username or password");
            Response response = new Response(new CustomError(ErrorConstants.VALIDATION_FAILED));
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
        }
    }

    @GetMapping("fetch/{mobile}")
    public ResponseEntity<Response> getAllCustomersByMobile(@PathVariable(value = "mobile") String mobile,
                                                            @RequestHeader("username") String username,
                                                            @RequestHeader("password") String password) {

        log.info("Request for get all customers by mobile");
        if(userDetailsService.validateUser(username,password)) {
            List<Customer> customers = customerService.getAllCustomersByMobile(mobile);
            Response response = new Response(customers);
            log.info("Fetched {} customers by mobile", customers.size());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            log.info("Request failed due to wrong username or password");
            Response response = new Response(new CustomError(ErrorConstants.VALIDATION_FAILED));
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
        }
    }

    @PostMapping("create")
    public ResponseEntity<Response> createCustomer(@RequestBody Customer customer,
                                                   @RequestHeader("username") String username,
                                                   @RequestHeader("password") String password) {

        log.info("Request for creating customer details");
        if(userDetailsService.validateUser(username,password)) {
            Response response = new Response(customerService.createCustomer(customer));

            log.info("Updated Request {} for creating customer details successfully", customer);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            log.info("Request failed due to wrong username or password");
            Response response = new Response(new CustomError(ErrorConstants.VALIDATION_FAILED));
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
        }
    }

    @PatchMapping("update")
    public ResponseEntity<Response> patchCustomerItem(@RequestBody Customer customer,
                                                   @RequestHeader("username") String username,
                                                   @RequestHeader("password") String password) {

        log.info("Request for updating customer details");
        if(userDetailsService.validateUser(username,password)) {
            Response response = new Response(customerService.patchCustomerItem(customer));

            log.info("Updated Request {} for updating customer details successfully", customer);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            log.info("Request failed due to wrong username or password");
            Response response = new Response(new CustomError(ErrorConstants.VALIDATION_FAILED));
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
        }
    }
}

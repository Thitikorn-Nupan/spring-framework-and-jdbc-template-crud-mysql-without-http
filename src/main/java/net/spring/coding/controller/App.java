package net.spring.coding.controller;

import net.spring.coding.config.Configuration;
import net.spring.coding.model.Customer;
import net.spring.coding.repository.CustomerRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.List;

public class App {


    private AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(Configuration.class); /* load spring with AnnotationConfigApplicationContext(< my config class >) */
    private CustomerRepository customerRepository = context.getBean("customerService" , CustomerRepository.class); /* retrieve bean get to object */
    private void create() {

        Customer customer = new Customer();
        customer.setFirstname("Alice");
        customer.setAge(38);
        customer.setEmail("Alice@hotmail.com");
        customer.setAddress("5525 Old Town Street");

        /* call method beans */
        System.out.println(customerRepository.create(customer));

        context.close();
    }
    private void reads() {

        /* call method beans */
        HashMap<String , List<Customer> > response = customerRepository.reads();

        for (int i = 0 ; i < response.get("customers").size() ; i++) {
            System.out.println(response.get("customers").get(i));
        }

        context.close();;
    }
    private void read() {

        HashMap<String,Customer> res = customerRepository.read(1L);
        System.out.println(res);

        System.out.println("\n#################\n");

        HashMap<String,Customer> res2 = customerRepository.read2(1L);
        System.out.println(res2);
    }
    private void update() {

        System.out.println(customerRepository.read(1l));

        Customer customerFix = new Customer();
        customerFix.setFirstname("Peat");
        customerFix.setAge(33);
        customerFix.setEmail("Peat@hotmail.com");
        customerFix.setAddress("7214 Street Old School");

        customerRepository.update(customerFix , 1l);

        System.out.println(customerRepository.read(1l));

    }
    private void delete() {
        System.out.println(customerRepository.read(30l));
        customerRepository.delete(30l);
        System.out.println(customerRepository.read(30l));
    }
    public static void main( String[] args ) {
        new App();
    }
}

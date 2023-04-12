package net.spring.coding.service;

import net.spring.coding.model.Customer;
import net.spring.coding.repository.CustomerRepository;
import net.spring.coding.service.mapperservice.CustomerMapper;
import net.spring.coding.service.sqlservice.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CustomerService implements CustomerRepository<Customer> {
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;
    @Autowired
    public CustomerService (DriverManagerDataSource dataSource) {
        /*
            CDI
            works like this
            DriverManagerDataSource dataSource = new DriverManagerDataSource(); it will return info my db ?
            jdbcTemplate = new JdbcTemplate(dataSource); not sure
        */
        jdbcTemplate = new JdbcTemplate(dataSource);
        sqlService = new SqlService();
    }

    @Override
    public HashMap<?, ?> create(Customer object) {

        HashMap<String,Customer> response = new HashMap<>();
        int row = jdbcTemplate.update(sqlService.getCreate() ,
                object.getFirstname(),object.getAge(),object.getEmail(),object.getAddress());
        if (row > 0) {

            response.put("successfully create" , object);
        }
        else {
            response.put("failed to create" , new Customer());
        }
        return response;
    }

    @Override
    public HashMap<?, List<Customer>> reads() {

        HashMap<String,List<Customer>> response = new HashMap<>();
        CustomerMapper customerMapper = new CustomerMapper();
        List<Customer> customerList = jdbcTemplate.query(sqlService.getReads(),customerMapper);
        if (customerList.size() > 0) {
            response.put("customers",customerList);
        }
        else {
            response.put("customers",new ArrayList<>());
        }
        return response;
    }

    @Override
    public HashMap<?, Customer> read(Long id) {
        HashMap<String,Customer> response = new HashMap<>();
        Object[]arg = {id};
        Customer customer = jdbcTemplate.queryForObject(sqlService.getReadById(),arg, (rs , rows) -> {
            /* reduce code  mapper */
            Customer customerSet  =  new Customer();
            customerSet.setId(rs.getLong("id"));
            customerSet.setFirstname(rs.getString("firstname"));
            customerSet.setAge(rs.getInt("age"));
            customerSet.setEmail(rs.getString("email"));
            customerSet.setAddress(rs.getString("Address"));
            return customerSet;

        });

        if(customer != null) {
            response.put("customer order "+id , customer);
        }
        else {
            response.put("customer order "+id , new Customer());
        }
        return response;
    }

    @Override
    public HashMap<?, Customer> read2(Long id) {
        HashMap<String,Customer> response = new HashMap<>();
        Object[]arg = {id};
        CustomerMapper customerMapper = new CustomerMapper();
        Customer customer = jdbcTemplate.queryForObject(sqlService.getReadById(),arg, customerMapper );
        if(customer != null) {
            response.put("customer order "+id , customer);
        }
        else {
            response.put("customer order "+id , new Customer());
        }
        return response;
    }

    @Override
    public HashMap<?, Customer> update(Customer object , Long id) {
        HashMap<String,Customer> response = new HashMap<>();
        int row = jdbcTemplate.update(sqlService.getUpdateById() , object.getFirstname()
        , object.getAge() , object.getEmail() , object.getAddress() , id);
        if (row > 0) {
            response.put("successfully update" , object);
        }
        else {
            response.put("failed to update" , new Customer());
        }
        return response;
    }

    @Override
    public HashMap<?, Customer> delete(Long id) {
        HashMap<String,Customer> response = new HashMap<>();
        Object[]arg = {id};
        CustomerMapper customerMapper = new CustomerMapper();
        jdbcTemplate.update(sqlService.getDeleteById() , id);
        Customer customer = jdbcTemplate.queryForObject(sqlService.getReadById(),arg, customerMapper );
        if (customer != null) {
            response.put("successfully delete" , customer);
        }
        else {
            response.put("failed to delete" , new Customer());
        }
        return response;
    }
}

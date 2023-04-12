package net.spring.coding.service.mapperservice;

import net.spring.coding.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int rowNum)  {
        Customer customer = new Customer();
        try {

            customer.setId(resultSet.getLong("id"));
            customer.setFirstname(resultSet.getString("firstname"));
            customer.setAge(resultSet.getInt("age"));
            customer.setEmail(resultSet.getString("email"));
            customer.setAddress(resultSet.getString("Address"));

        } catch (SQLException errors) {

            System.out.println("Retrieve database was Failed..."+errors);

        }
        return customer;
    }
}

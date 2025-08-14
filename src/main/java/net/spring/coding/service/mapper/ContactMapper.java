package net.spring.coding.service.mapper;

import net.spring.coding.model.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet resultSet, int rowNum)  {
        Contact contact = new Contact();
        try {
            contact.setId(resultSet.getLong("id"));
            contact.setFirstname(resultSet.getString("firstname"));
            contact.setAge(resultSet.getInt("age"));
            contact.setEmail(resultSet.getString("email"));
            contact.setAddress(resultSet.getString("Address"));
        } catch (SQLException errors) {
            throw new RuntimeException(errors);
        }
        return contact;
    }
}

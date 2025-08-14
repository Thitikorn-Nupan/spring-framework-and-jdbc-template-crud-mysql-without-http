package net.spring.coding.service;

import net.spring.coding.model.Contact;
import net.spring.coding.repository.ModelRepository;
import net.spring.coding.service.mapper.ContactMapper;
import net.spring.coding.service.sql.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class ModelService implements ModelRepository<Contact> {
    private final JdbcTemplate jdbcTemplate;
    private final SqlService sqlService;
    /**
        CDI
        works like this
        DriverManagerDataSource dataSource = new DriverManagerDataSource(); it will return info my db ?
        jdbcTemplate = new JdbcTemplate(dataSource); not sure
    */
    @Autowired
    public ModelService(DriverManagerDataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        sqlService = new SqlService();
    }

    @Override
    public HashMap<?, ?> create(Contact object) {
        HashMap<String, Contact> response = new HashMap<>();
        int row = jdbcTemplate.update(sqlService.getCreate() ,
                object.getFirstname(),object.getAge(),object.getEmail(),object.getAddress());
        if (row > 0) {
            response.put("successfully create" , object);
        }
        else {
            response.put("failed to create" , new Contact());
        }
        return response;
    }

    @Override
    public HashMap<?, List<Contact>> reads() {
        HashMap<String,List<Contact>> response = new HashMap<>();
        ContactMapper contactMapper = new ContactMapper();
        List<Contact> contactList = jdbcTemplate.query(sqlService.getReads(), contactMapper);
        if (!contactList.isEmpty()) {
            response.put("customers", contactList);
        }
        else {
            response.put("customers",new ArrayList<>());
        }
        return response;
    }

    @Override
    public HashMap<?, Contact> read(Long id) {
        HashMap<String, Contact> response = new HashMap<>();
        Object[]arg = {id};
        Contact contact = jdbcTemplate.queryForObject(sqlService.getReadById(),arg, (rs , rows) -> {
            // reduce code  mapper by lambda
            Contact contactSet =  new Contact();
            contactSet.setId(rs.getLong("id"));
            contactSet.setFirstname(rs.getString("firstname"));
            contactSet.setAge(rs.getInt("age"));
            contactSet.setEmail(rs.getString("email"));
            contactSet.setAddress(rs.getString("Address"));
            return contactSet;
        });
        if(contact != null) {
            response.put("customer order "+id , contact);
        }
        else {
            response.put("customer order "+id , new Contact());
        }
        return response;
    }

    @Override
    public HashMap<?, Contact> read2(Long id) {
        HashMap<String, Contact> response = new HashMap<>();
        Object[]arg = {id};
        ContactMapper contactMapper = new ContactMapper();
        Contact contact = jdbcTemplate.queryForObject(sqlService.getReadById(),arg, contactMapper);
        if(contact != null) {
            response.put("customer order "+id , contact);
        }
        else {
            response.put("customer order "+id , new Contact());
        }
        return response;
    }

    @Override
    public HashMap<?, Contact> update(Contact object , Long id) {
        HashMap<String, Contact> response = new HashMap<>();
        int row = jdbcTemplate.update(sqlService.getUpdateById() , object.getFirstname()
        , object.getAge() , object.getEmail() , object.getAddress() , id);
        if (row > 0) {
            response.put("successfully update" , object);
        }
        else {
            response.put("failed to update" , new Contact());
        }
        return response;
    }

    @Override
    public HashMap<?, Contact> delete(Long id) {
        HashMap<String, Contact> response = new HashMap<>();
        int row = jdbcTemplate.update(sqlService.getDeleteById() , id);
        if (row > 0) {
            response.put("successfully delete" , new Contact()); // just for testing
        }
        else {
            response.put("failed to delete" , new Contact());
        }
        return response;
    }
}

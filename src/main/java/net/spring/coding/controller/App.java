package net.spring.coding.controller;

import net.spring.coding.config.Configuration;
import net.spring.coding.model.Contact;
import net.spring.coding.repository.ModelRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;
import java.util.HashMap;
import java.util.List;

public class App {


    private final AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(Configuration.class); // load spring with AnnotationConfigApplicationContext(< my config class >)
    private final ModelRepository modelRepository = context.getBean("modelService", ModelRepository.class); // retrieve bean get to object

    private void create() {
        Contact contact = new Contact();
        contact.setFirstname("Max");
        contact.setAge(34);
        contact.setEmail("Max@hotmail.com");
        contact.setAddress("5 Old Town Street");
        // call method beans
        Assert.notNull(modelRepository.create(contact).get("successfully create"), "failed to create contact");
        context.close();
    }
    private void reads() {
        // call method beans
        HashMap<?, List> response = modelRepository.reads();
        Assert.noNullElements(response.get("customers").toArray(), "failed to reads contact");
        context.close();
    }
    private void read() {
        // call method beans
        HashMap<String, Contact> response = modelRepository.read(2L);
        Assert.notNull(response.get("customer order 2"), "failed to read contact");
    }

    private void update() {
        Contact contactFix = new Contact();
        contactFix.setFirstname("Peat");
        contactFix.setAge(33);
        contactFix.setEmail("Peat@hotmail.com");
        contactFix.setAddress("7214 Street Old School");
        HashMap<String, Contact> response = modelRepository.update(contactFix, 2L);
        Assert.notNull(response.get("successfully update"), "failed to update contact");
    }

    private void delete() {
        HashMap<String, Contact> response = modelRepository.delete(8L);
        Assert.notNull(response.get("successfully delete"), "failed to delete contact");
    }

    public static void main( String[] args ) {
        new App().delete();
    }
}

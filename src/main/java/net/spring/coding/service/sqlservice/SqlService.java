package net.spring.coding.service.sqlservice;

public class SqlService {
    private final String create = "insert into contact(firstname, age, email , address) values(?,?,?,?) ;";
    private final String reads = "select * from contact ;";
    private final String readById = "select * from contact where id = ?;";
    private final String updateById = "update contact set firstname = ? , age = ? , email = ? , address = ? where id = ? ;";
    private final String deleteById = "delete from contact where id = ?";


    public String getCreate() {
        return create;
    }

    public String getReads() {
        return reads;
    }

    public String getReadById() {
        return readById;
    }

    public String getUpdateById() {
        return updateById;
    }

    public String getDeleteById() {
        return deleteById;
    }
}

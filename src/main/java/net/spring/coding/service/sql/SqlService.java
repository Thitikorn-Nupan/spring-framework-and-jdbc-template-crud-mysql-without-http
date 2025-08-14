package net.spring.coding.service.sql;

public class SqlService {
    private final String create = "insert into contacts(firstname, age, email , address) values(?,?,?,?) ;";
    private final String reads = "select * from contacts ;";
    private final String readById = "select * from contacts where id = ?;";
    private final String updateById = "update contacts set firstname = ? , age = ? , email = ? , address = ? where id = ? ;";
    private final String deleteById = "delete from contacts where id = ?";
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

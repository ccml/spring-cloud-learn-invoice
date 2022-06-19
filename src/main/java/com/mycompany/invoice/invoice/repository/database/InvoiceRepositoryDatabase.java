/*
package com.mycompany.invoice.core.repository.database;

import com.mycompany.invoice.core.entity.invoice.Invoice;
import com.mycompany.invoice.core.repository.InvoiceRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InvoiceRepositoryDatabase implements InvoiceRepositoryInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Invoice> list() {
        return jdbcTemplate.query(
                "select INVOICE_NUMBER, CUSTOMER_NAME from INVOICE",
                (rs, rowNum) ->
                        new Invoice(String.valueOf(rs.getLong("INVOICE_NUMBER")), rs.getString("CUSTOMER_NAME"))
        );
    }

    @Override
    public Invoice getById(String number) {
        return jdbcTemplate.queryForObject(
                "select INVOICE_NUMBER, CUSTOMER_NAME, ORDER_NUMBER from INVOICE where INVOICE_NUMBER=?",
                new Object[] { number },
                (rs, rowNum) ->
                        new Invoice(String.valueOf(rs.getLong("INVOICE_NUMBER")), rs.getString("CUSTOMER_NAME"), rs.getString("ORDER_NUMBER"))
        );
    }


    public Invoice create(Invoice invoice) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "insert into INVOICE (CUSTOMER_NAME, ORDER_NUMBER) values (?,?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, invoice.getCustomerName());
                ps.setString(2, invoice.getOrderNumber());
                return ps;
        }, kh);
        invoice.setNumber(kh.getKey().toString());
        System.out.println("Database : Invoice added with number " + invoice.getNumber() + " for " + invoice.getCustomerName());
        return invoice;
    }
}
*/
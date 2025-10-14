package com.vev.exemplo.bancodados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDao {

    private final Connection connection;

    public InvoiceDao(Connection connection) {
        this.connection = connection;
    }

    public List<Invoice> all() throws DatabaseAccessException {
        try (PreparedStatement ps = connection.prepareStatement("select name, value from invoice")) {
            ResultSet rs = ps.executeQuery();

            List<Invoice> allInvoices = new ArrayList<>();
            while (rs.next()) {
                allInvoices.add(new Invoice(rs.getString("name"), rs.getInt("value")));
            }

            return allInvoices;

        } catch (Exception e) {
            throw new DatabaseAccessException(e);
        }
    }

    public List<Invoice> allWithAtLeast(int value) throws DatabaseAccessException {
        try (PreparedStatement ps = connection.prepareStatement("select name, value from invoice where value >= ?")) {
            ps.setInt(1, value);
            ResultSet rs = ps.executeQuery();

            List<Invoice> allInvoices = new ArrayList<>();
            while (rs.next()) {
                allInvoices.add(new Invoice(rs.getString("name"), rs.getInt("value")));
            }
            return allInvoices;
        } catch (Exception e) {
            throw new DatabaseAccessException(e);
        }
    }

    public void save(Invoice inv) throws DatabaseAccessException {
        try (PreparedStatement ps = connection.prepareStatement("insert into invoice (name, value) values (?,?)")) {
            ps.setString(1, inv.customer);
            ps.setInt(2, inv.value);
            ps.execute();

            connection.commit();
        } catch (Exception e) {
            throw new DatabaseAccessException(e);
        }
    }

}
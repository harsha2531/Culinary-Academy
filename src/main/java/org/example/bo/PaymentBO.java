package org.example.bo;

import org.example.dto.PaymentDTO;
import org.example.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    public boolean save(PaymentDTO dto) throws Exception;

    public boolean update(PaymentDTO dto) throws Exception;

    public boolean delete(String ID)throws Exception;

    Payment searchById(String id) throws SQLException, ClassNotFoundException;

    public String generateNextId() throws SQLException, ClassNotFoundException;
    public List<PaymentDTO> getAll() throws SQLException, ClassNotFoundException;
}

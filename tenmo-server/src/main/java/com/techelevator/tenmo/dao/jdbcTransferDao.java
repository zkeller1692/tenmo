package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class jdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    public jdbcTransferDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addTransfer(long transferId, long transferTypeId,
                            long accountFromId, long accountToId, BigDecimal transferAmount){
        String sql = "INSERT INTO transfer" +
                " (transfer_type_id, transfer_status_id, account_from, account_to, amount)" +
                "VALUES (?, ?, ?, ?, ?);";

        jdbcTemplate.update(sql, transferId, transferTypeId, accountFromId, accountToId, transferAmount);



    }
}

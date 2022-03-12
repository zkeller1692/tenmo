package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class jdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    public jdbcTransferDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }



    public Transfer getTransfer(Long transferId){
        Transfer transfer = null;
        String sql = "SELECT * " +
                    "FROM transfer " +
                    "WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        if(results.next()){
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    }

    @Override
    public void addTransfer(String origUsername, String destinUsername, BigDecimal transferAmt){
        String sql = "INSERT INTO transfer" +
                " (transfer_type_id, transfer_status_id, account_from, account_to, amount)" +
                "VALUES (2, 2, (SELECT account_id FROM account " +
                "JOIN tenmo_user ON tenmo_user.user_id = account.user_id " +
                "WHERE username = ?), (SELECT account_id FROM account " +
                "JOIN tenmo_user ON tenmo_user.user_id = account.user_id " +
                "WHERE username = ?), ?);";
        jdbcTemplate.update(sql, origUsername, destinUsername, transferAmt);
    }

    private Transfer mapRowToTransfer(SqlRowSet rowSet){
        Transfer transfer = new Transfer();
        transfer.setId(rowSet.getLong("transfer_id"));
        transfer.setAccountFromId(rowSet.getLong("account_from"));
        transfer.setAccountToId(rowSet.getLong("account_to"));
        transfer.setTransferAmount(rowSet.getBigDecimal("transfer_amount"));
        return transfer;
    }
    }

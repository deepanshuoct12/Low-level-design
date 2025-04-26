package org.upiSystem.dao;

import org.apache.commons.lang3.StringUtils;
import org.upiSystem.exception.BankNotFoundException;
import org.upiSystem.exception.InvalidInputException;
import org.upiSystem.model.Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BankDao {
    private static List<Bank> banks = new ArrayList<>();
    private static HashMap<String, Bank> idIndex = new HashMap<>();

    public void add(Bank bank) throws InvalidInputException {
        if (bank == null) {
            throw new InvalidInputException("Null bank provided");
        }

        banks.add(bank);
        idIndex.put(bank.getId(), bank);
    }

    public Bank get(String id) throws InvalidInputException, BankNotFoundException {
        if (StringUtils.isEmpty(id)) {
            throw new InvalidInputException("Invalid id provided : " +  id);
        }

        Bank bank = idIndex.get(id);
        if (bank == null) {
            throw new BankNotFoundException("Invalid bank id provided : " + id);
        }


        return bank;
    }

    public List<Bank> getAll() {
        return banks;
    }
}

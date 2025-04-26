package org.upiSystem.service;

import org.apache.commons.lang3.StringUtils;
import org.upiSystem.constants.BankStatus;
import org.upiSystem.dao.BankDao;
import org.upiSystem.exception.InvalidInputException;
import org.upiSystem.model.Bank;

public class BankService {
    private BankDao bankDao = new BankDao();

    public Bank getBank(String id) {
        try {
           return bankDao.get(id);
        } catch (Exception ex) {
            System.out.println("not able to fetch bank");
        }

        return null;
    }

    public void registerBank(String bankId, String bankName, BankStatus status)  {
        try {
            if (StringUtils.isEmpty(bankId) || StringUtils.isEmpty(bankName) || status == null) {
                throw new InvalidInputException("Invalid input : " + bankId + " , " + bankName + " , " + status);
            }

            Bank bank = new Bank(bankId, bankName, status);
            bankDao.add(bank);
        } catch (InvalidInputException ex) {
            System.out.println("not able to register bank : " + bankName);
        }
    }
}

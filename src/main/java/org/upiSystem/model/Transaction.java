package org.upiSystem.model;

import lombok.Data;
import org.upiSystem.constants.TransactionStatus;

import java.util.Date;

@Data
public class Transaction {
    private String id;
    private String userFrom;
    private String userTo;
    private String toAccountId;
    private String fromAccountId;
    private Long amount;
    private Date date;
    private TransferInfo transferInfo;
    private TransactionStatus transactionStatus;
}

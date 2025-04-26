package org.upiSystem.demo;

import org.upiSystem.constants.BankStatus;
import org.upiSystem.constants.UserRole;
import org.upiSystem.constants.UserStatus;
import org.upiSystem.model.Transaction;
import org.upiSystem.model.TransferInfo;
import org.upiSystem.model.User;
import org.upiSystem.service.BankService;
import org.upiSystem.service.IPaymentServiceProvider;
import org.upiSystem.service.PaymentServiceProvider;
import org.upiSystem.service.UserService;

import java.util.List;

public class UPIDriver {
    private UserService userService = new UserService();
    private BankService bankService = new BankService();

    private IPaymentServiceProvider paymentServideProvider = new PaymentServiceProvider();

    public void startExecution() {
        initBanks();
        initUsers();
        paymentServideProvider.linkBankAccount("u1", "b1", "acc1", 1000l);
        paymentServideProvider.linkBankAccount("u2", "b2", "acc2", 1000l);

        TransferInfo transferInfo = new TransferInfo("u1", "u2", 200l, "acc1", "acc2");
        paymentServideProvider.makePayment(transferInfo);
       List<Transaction> transactions = paymentServideProvider.searchTransactions("u1", UserRole.RECIEVER);
       if (transactions != null) {
           for (Transaction transaction : transactions) {
               System.out.println(transaction.getId());
           }
       }
    }

    private void initBanks() {
        bankService.registerBank("b1", "HDFC", BankStatus.UP);
        bankService.registerBank("b2", "AXIS", BankStatus.UP);
    }

    /**
     * doubt : mayank
     */
    private void initUsers() {
        User user1 = paymentServideProvider.onBoardUser("u1", "abhi", "987654222", UserStatus.ACTIVE);
        User user2 = paymentServideProvider.onBoardUser("u2", "ravi", "987694222", UserStatus.ACTIVE);
    }
}

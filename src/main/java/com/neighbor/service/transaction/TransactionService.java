package com.neighbor.service.transaction;

import com.neighbor.model.transaction.Transaction;

public interface TransactionService {
    Transaction createNewTransaction(Transaction transaction);
}

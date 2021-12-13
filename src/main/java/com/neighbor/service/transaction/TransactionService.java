package com.neighbor.service.transaction;

import com.neighbor.model.Agent;
import com.neighbor.model.transaction.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createNewTransaction(Transaction transaction);
    List<Transaction> getTransactionsListByAgent(int agentId);
    Transaction getTransactionById(int transactionId);
}

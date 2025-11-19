package service;

import entities.Transaction;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AuditService {
    private static final AuditService INSTANCE = new AuditService();
    private final List<Transaction> transactionLog;

    private AuditService() {
        this.transactionLog = new CopyOnWriteArrayList<>();
    }

    public static AuditService getInstance() {
        return INSTANCE;
    }

    public void log(Transaction transaction) {
        this.transactionLog.add(transaction);
    }

    public void printAuditLog() {
        System.out.println("\n--- Audit Log ---");
        transactionLog.forEach(System.out::println);
        System.out.println("-----------------");
    }
}
package org.poo.main.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Represents a split payment transaction involving multiple accounts.
 */
public final class SplitPaymentTransaction implements Transaction {
    private final String description;
    private final double amount;
    private final int timestamp;
    private final String currency;
    private final List<String> involvedAccounts;
    private final String transactionType;

    public SplitPaymentTransaction(
            final double amount,
            final int timestamp,
            final String currency,
            final List<String> involvedAccounts
    ) {
        this.amount = amount;
        this.timestamp = timestamp;
        this.currency = currency;
        this.involvedAccounts = involvedAccounts;
        this.description = "Split payment of ";
        this.transactionType = "split";
    }

    public String getDescription() {
        return description;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getCurrency() {
        return currency;
    }

    public List<String> getAccountsInvolved() {
        return involvedAccounts;
    }

    @Override
    public ObjectNode toObjectNode() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode transactionNode = mapper.createObjectNode();

        transactionNode.put("timestamp", this.getTimestamp());

        DecimalFormat df = new DecimalFormat("#.00");
        transactionNode.put(
                "description",
                this.getDescription() + df.format(this.getAmount()) + " " + this.getCurrency()
        );

        transactionNode.put("currency", this.getCurrency());
        transactionNode.put("amount", this.getAmount() / this.getAccountsInvolved().size());

        ArrayNode involvedAccountsNode = mapper.createArrayNode();
        for (String account : this.getAccountsInvolved()) {
            involvedAccountsNode.add(account);
        }

        transactionNode.set("involvedAccounts", involvedAccountsNode);

        return transactionNode;
    }
}

package org.poo.main.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Represents a split payment transaction that fails due to insufficient funds in one account.
 */
@Getter
public final class SplitNotEnoughTransaction implements Transaction {
    private final String description;
    private final double amount;
    private final int timestamp;
    private final String currency;
    private final List<String> involvedAccounts;
    private final String transactionType;
    private final String errorAccount;

    public SplitNotEnoughTransaction(final double amount, final int timestamp, final String currency,
                                     final List<String> involvedAccounts, final String errorAccount) {
        this.amount = amount;
        this.timestamp = timestamp;
        this.currency = currency;
        this.involvedAccounts = involvedAccounts;
        this.description = "Split payment of ";
        this.transactionType = "split";
        this.errorAccount = errorAccount;
    }

    @Override
    public ObjectNode toObjectNode() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode transactionNode = mapper.createObjectNode();

        transactionNode.put("timestamp", this.getTimestamp());

        DecimalFormat df = new DecimalFormat("#.00");
        transactionNode.put("description", this.getDescription() +
                df.format(this.getAmount()) + " " + this.getCurrency());

        transactionNode.put("currency", this.getCurrency());
        transactionNode.put("amount", this.getAmount()
                / this.involvedAccounts.size());

        ArrayNode involvedAccountsNode = mapper.createArrayNode();
        for (String account : this.getInvolvedAccounts()) {
            involvedAccountsNode.add(account);
        }
        transactionNode.set("involvedAccounts", involvedAccountsNode);

        transactionNode.put("error", "Account " +
                this.getErrorAccount() +
                " has insufficient funds for a split payment.");

        return transactionNode;
    }
}

package org.poo.main.OutputJson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.main.Card.Card;
import org.poo.main.User;
import org.poo.main.Account.Account;
import java.util.ArrayList;
import java.util.HashMap;

public final class PrintUsersOutput {
    private PrintUsersOutput() {
        // private constructor to hide implicit public one
    }

    /**
     * Generates output for printing users and their details.
     *
     * @param outputList List to add the output to
     * @param users Map of users to print
     * @param timestamp Current timestamp
     */
    public static void generatePrintUsersOutput(
            final ArrayList<ObjectNode> outputList,
            final HashMap<String, User> users,
            final int timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode commandNode = mapper.createObjectNode();
        commandNode.put("command", "printUsers");
        ArrayNode outputArray = mapper.createArrayNode();

        for (HashMap.Entry<String, User> entry : users.entrySet()) {
            User user = entry.getValue();
            ObjectNode userNode = mapper.createObjectNode();
            userNode.put("firstName", user.getName());
            userNode.put("lastName", user.getSurname());
            userNode.put("email", user.getEmail());
            ArrayNode accountsArray = mapper.createArrayNode();
            for (Account account : user.getAccounts().values()) {
                if (account != null) {
                    ObjectNode accountNode = mapper.createObjectNode();
                    accountNode.put("IBAN", account.getIBAN());
                    accountNode.put("balance", account.getBalance());
                    accountNode.put("currency", account.getCurrency());
                    accountNode.put("type", account.getType());
                    ArrayNode cardsArray = mapper.createArrayNode();
                    if (account.getCards() != null) {
                        for (Card card : account.getCards()) {
                            ObjectNode cardNode = mapper.createObjectNode();
                            cardNode.put("cardNumber", card.getCardNumber());
                            cardNode.put("status", card.getStatus());
                            cardsArray.add(cardNode);
                        }
                    }
                    accountNode.set("cards", cardsArray);
                    accountsArray.add(accountNode);
                }
            }
            userNode.set("accounts", accountsArray);
            outputArray.add(userNode);
        }
        commandNode.set("output", outputArray);
        commandNode.put("timestamp", timestamp);
        outputList.add(commandNode);
    }
}

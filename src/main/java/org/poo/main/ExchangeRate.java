package org.poo.main;

import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class responsible for managing currency exchange rates and calculating conversion rates.
 * This class is not designed for extension.
 */
@Getter
@Setter
public final class ExchangeRate {
    private final Map<String, Map<String, Double>> exchangeRates;

    /**
     * Constructs a new ExchangeRate instance with empty exchange rates map.
     */
    public ExchangeRate() {
        this.exchangeRates = new HashMap<>();
    }

    /**
     * Adds a new exchange rate between two currencies.
     * Also adds the inverse rate automatically.
     *
     * @param from source currency code
     * @param to target currency code
     * @param rate exchange rate from source to target currency
     */
    public void addExchangeRate(final String from, final String to, final double rate) {
        exchangeRates.putIfAbsent(from, new HashMap<>());
        exchangeRates.putIfAbsent(to, new HashMap<>());
        exchangeRates.get(from).put(to, rate);
        exchangeRates.get(to).put(from, 1 / rate);
    }

    /**
     * Gets the exchange rate between two currencies.
     * Uses DFS to find a conversion path if direct conversion is not available.
     *
     * @param from source currency code
     * @param to target currency code
     * @return the exchange rate
     * @throws IllegalArgumentException if currencies not found or no conversion path exists
     */
    public double getExchangeRate(final String from, final String to) {
        if (!exchangeRates.containsKey(from) || !exchangeRates.containsKey(to)) {
            throw new IllegalArgumentException("Currency not found");
        }

        if (from.equals(to)) {
            return 1.0;
        }

        Set<String> visited = new HashSet<>();
        Double result = dfs(from, to, 1.0, visited);

        if (result == null) {
            throw new IllegalArgumentException(
                    "No exchange rate path exists between " + from + " and " + to
            );
        }

        return result;
    }

    /**
     * Performs depth-first search to find a conversion path between currencies.
     *
     * @param current current currency in the path
     * @param target target currency we're trying to reach
     * @param currentRate accumulated exchange rate along the path
     * @param visited set of visited currencies to avoid cycles
     * @return the final exchange rate if a path is found, null otherwise
     */
    private Double dfs(final String current, final String target,
                       final double currentRate, final Set<String> visited) {
        if (visited.contains(current)) {
            return null;
        }

        if (current.equals(target)) {
            return currentRate;
        }

        visited.add(current);

        Map<String, Double> neighbors = exchangeRates.get(current);
        if (neighbors == null) {
            return null;
        }

        for (Map.Entry<String, Double> neighbor : neighbors.entrySet()) {
            if (!visited.contains(neighbor.getKey())) {
                Double result = dfs(
                        neighbor.getKey(),
                        target,
                        currentRate * neighbor.getValue(),
                        new HashSet<>(visited)
                );

                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }
}

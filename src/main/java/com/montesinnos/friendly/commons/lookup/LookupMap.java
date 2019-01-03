package com.montesinnos.friendly.commons.lookup;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Handy library to create a look up with some basic cleaning to improve the match rate
 * In the future, this should be configurable so users can decide which cleaning should happen
 */
public class LookupMap implements Serializable {
    private final HashMap<String, String> map = new HashMap<>();

    protected LookupMap(final Map<String, String> input) {
        add(input);
    }

    public int add(final Map<String, String> input) {
        for (final Map.Entry<String, String> entry : input.entrySet()) {
            map.put(keyNormalizer(entry.getKey()), entry.getValue());
        }
        return map.size();
    }

    /**
     * Normalizes the key for the map so it increases the map rate
     *
     * @param key to be normalized
     * @return new String key to be stored and searched
     */
    private String keyNormalizer(final String key) {
        return key.replace(" ", "").toUpperCase().trim();
    }

    public Optional<String> get(final String lookup) {
        return Optional.ofNullable(map.get(keyNormalizer(lookup)));
    }

    public int size() {
        return map.size();
    }
}

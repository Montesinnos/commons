package com.montesinnos.friendly.commons.lookup;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Handy library to create a look up with some basic cleaning to improve the match rate
 * In the future, this should be configurable so users can decide which cleaning should happen
 */
public class LookupMapFactory {
    public static LookupMap fromListWithSeparator(final List<String> input, final String separator) {
        return new LookupMap(
                input.stream()
                        .map(x -> x.split(separator))
                        .filter(x -> x.length > 1)
                        .collect(
                                Collectors.toMap(
                                        x -> x[0],
                                        y -> y[1],
                                        (s, a) -> s)));
    }

    public static LookupMap fromMap(final Map<String, String> input) {
        return new LookupMap(input);
    }

    public static LookupMap fromMaps(final Map<String, String>... input) {
        return new LookupMap(
                Stream.of(input)
                        .flatMap(map -> map.entrySet().stream())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue)
                        ));
    }
}

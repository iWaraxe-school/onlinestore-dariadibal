package by.issoft.domain;

import by.issoft.config.SortDirection;
import by.issoft.config.XMLParser;

import java.util.*;

public class ProductComparator {

    private static final Comparator<Product> NAME_COMPARATOR = Comparator.comparing(Product::getName, String::compareToIgnoreCase);
    private static final Comparator<Product> PRICE_COMPARATOR = Comparator.comparing(Product::getPrice);
    private static final Comparator<Product> RATE_COMPARATOR = Comparator.comparing(Product::getRate);
    private static final LinkedHashMap<String, SortDirection> TOP_5_MAP;

    static {
        TOP_5_MAP = new LinkedHashMap<>();
        TOP_5_MAP.put("price", SortDirection.DESC);
    }

    public static Comparator<Product> generalComparator = getGeneralComparator(XMLParser.getSortConfig());
    public static Comparator<Product> top5Comparator = getGeneralComparator(TOP_5_MAP);
    //public static Comparator<Product> top5Comparator = PRICE_COMPARATOR.reversed();

    private static Comparator<Product> getGeneralComparator(LinkedHashMap<String, SortDirection> sortMap) {
        List<Comparator<Product>> comparatorList = new LinkedList<>();
        sortMap.forEach((k, v) -> {
            Comparator<Product> currentComparator = switch (k.toLowerCase(Locale.ROOT)) {
                case "name" -> NAME_COMPARATOR;
                case "price" -> PRICE_COMPARATOR;
                case "rate" -> RATE_COMPARATOR;
                default -> null;
            };
            if (currentComparator != null && v.equals(SortDirection.DESC)) {
                currentComparator = currentComparator.reversed();
            }
            comparatorList.add(currentComparator);
        });

        if (!comparatorList.isEmpty()) {
            Comparator<Product> comparator = comparatorList.get(0);
            for (int i = 1; i < comparatorList.size(); i++) {
                comparator = comparator.thenComparing(comparatorList.get(i));
            }
            return comparator;
        } else {
            return NAME_COMPARATOR;
        }
    }
}

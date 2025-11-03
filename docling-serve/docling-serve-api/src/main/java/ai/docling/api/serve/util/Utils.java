package ai.docling.api.serve.util;

import static ai.docling.api.serve.util.ValidationUtils.ensureNotEmpty;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Utility methods.
 */
public final class Utils {
    private Utils() {}

    /**
     * Returns the first non-null value from the provided array of values.
     * If all values are null, an new IllegalArgumentException is thrown.
     *
     * @param name   A non-null string representing the name associated with the values.
     * @param values An array of potentially nullable values to search through.
     * @return The first non-null value in the array.
     * @throws IllegalArgumentException If all values are null or if the array is empty.
     */
    @SafeVarargs
    @NonNull
    public static <T> T firstNotNull(@NonNull String name, @Nullable T... values) {
        ensureNotEmpty(values, name + " values");

        return Stream.of(values)
            .filter(Objects::nonNull)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("At least one of the given '%s' values must be not null".formatted(name)));
    }

    /**
     * Returns the given value if it is not {@code null}, otherwise returns the given default value.
     *
     * @param value        The value to return if it is not {@code null}.
     * @param defaultValue The value to return if the value is {@code null}.
     * @param <T>          The type of the value.
     * @return the given value if it is not {@code null}, otherwise returns the given default value.
     */
    public static <T> T getOrDefault(T value, T defaultValue) {
      return Optional.ofNullable(value).orElse(defaultValue);
    }

    /**
     * Returns the given list if it is not {@code null} and not empty, otherwise returns the given default list.
     *
     * @param list        The list to return if it is not {@code null} and not empty.
     * @param defaultList The list to return if the list is {@code null} or empty.
     * @param <T>         The type of the value.
     * @return the given list if it is not {@code null} and not empty, otherwise returns the given default list.
     */
    public static <T> List<T> getOrDefault(List<T> list, List<T> defaultList) {
        return isNullOrEmpty(list) ? defaultList : list;
    }

    /**
     * Returns the given map if it is not {@code null} and not empty, otherwise returns the given default map.
     *
     * @param map        The map to return if it is not {@code null} and not empty.
     * @param defaultMap The map to return if the map is {@code null} or empty.
     * @return the given map if it is not {@code null} and not empty, otherwise returns the given default map.
     */
    public static <K, V> Map<K, V> getOrDefault(Map<K, V> map, Map<K, V> defaultMap) {
        return isNullOrEmpty(map) ? defaultMap : map;
    }

    /**
     * Returns the given value if it is not {@code null}, otherwise returns the value returned by the given supplier.
     *
     * @param value                The value to return if it is not {@code null}.
     * @param defaultValueSupplier The supplier to call if the value is {@code null}.
     * @param <T>                  The type of the value.
     * @return the given value if it is not {@code null}, otherwise returns the value returned by the given supplier.
     */
    public static <T> T getOrDefault(@Nullable T value, Supplier<T> defaultValueSupplier) {
      return Optional.ofNullable(value).orElseGet(defaultValueSupplier::get);
    }

    /**
     * Is the given string {@code null} or blank?
     *
     * @param string The string to check.
     * @return true if the string is {@code null} or blank.
     */
    public static boolean isNullOrBlank(String string) {
        return string == null || string.trim().isBlank();
    }

    /**
     * Is the given string {@code null} or empty ("")?
     *
     * @param string The string to check.
     * @return true if the string is {@code null} or empty.
     */
    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    /**
     * Is the given string not {@code null} and not blank?
     *
     * @param string The string to check.
     * @return true if there's something in the string.
     */
    public static boolean isNotNullOrBlank(String string) {
        return !isNullOrBlank(string);
    }

    /**
     * Is the given string not {@code null} and not empty ("")?
     *
     * @param string The string to check.
     * @return true if the given string is not {@code null} and not empty ("")?
     */
    public static boolean isNotNullOrEmpty(String string) {
        return !isNullOrEmpty(string);
    }

    /**
     * Is the collection {@code null} or empty?
     *
     * @param collection The collection to check.
     * @return {@code true} if the collection is {@code null} or {@link Collection#isEmpty()}, otherwise {@code false}.
     */
    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return (collection == null) || collection.isEmpty();
    }

    /**
     * Is the iterable object {@code null} or empty?
     *
     * @param iterable The iterable object to check.
     * @return {@code true} if the iterable object is {@code null} or there are no objects to iterate over, otherwise {@code false}.
     */
    public static <T> boolean isNullOrEmpty(Iterable<T> iterable) {
        return (iterable == null) || !iterable.iterator().hasNext();
    }

    /**
     * Utility method to check if an array is null or has no elements.
     *
     * @param array the array to check
     * @return {@code true} if the array is null or has no elements, otherwise {@code false}
     */
    public static <T> boolean isNullOrEmpty(T[] array) {
        return (array == null) || (array.length == 0);
    }

    /**
     * Is the map object {@code null} or empty?
     *
     * @param map The iterable object to check.
     * @return {@code true} if the map object is {@code null} or empty map, otherwise {@code false}.
     */
    public static <K, V> boolean isNullOrEmpty(@Nullable Map<K, V> map) {
        return (map == null) || map.isEmpty();
    }
}

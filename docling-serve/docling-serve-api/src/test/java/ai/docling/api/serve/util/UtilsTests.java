package ai.docling.api.serve.util;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UtilsTests {
  @Test
    void get_or_default() {
        assertThat(Utils.getOrDefault("foo", "bar")).isEqualTo("foo");
        assertThat(Utils.getOrDefault(null, "bar")).isEqualTo("bar");

        assertThat(Utils.getOrDefault("foo", () -> "bar")).isEqualTo("foo");
        assertThat(Utils.getOrDefault(null, () -> "bar")).isEqualTo("bar");
    }

    @Test
    void get_or_default_list() {
        List<Integer> nullList = null;
        List<Integer> emptyList = List.of();
        List<Integer> list1 = List.of(1);
        List<Integer> list2 = List.of(2);

        assertThat(Utils.getOrDefault(nullList, nullList)).isSameAs(nullList);
        assertThat(Utils.getOrDefault(nullList, emptyList)).isSameAs(emptyList);

        assertThat(Utils.getOrDefault(emptyList, nullList)).isSameAs(nullList);
        assertThat(Utils.getOrDefault(emptyList, emptyList)).isSameAs(emptyList);

        assertThat(Utils.getOrDefault(nullList, list1)).isSameAs(list1);
        assertThat(Utils.getOrDefault(emptyList, list1)).isSameAs(list1);

        assertThat(Utils.getOrDefault(list1, list2)).isSameAs(list1).isNotSameAs(list2);
    }

    @Test
    void get_or_default_map() {
        Map<String, String> nullMap = null;
        Map<String, String> emptyMap = Map.of();
        Map<String, String> map1 = Map.of("one", "1");
        Map<String, String> map2 = Map.of("two", "2");

        assertThat(Utils.getOrDefault(nullMap, nullMap)).isSameAs(nullMap);
        assertThat(Utils.getOrDefault(nullMap, emptyMap)).isSameAs(emptyMap);

        assertThat(Utils.getOrDefault(emptyMap, nullMap)).isSameAs(nullMap);
        assertThat(Utils.getOrDefault(emptyMap, emptyMap)).isSameAs(emptyMap);

        assertThat(Utils.getOrDefault(nullMap, map1)).isSameAs(map1);
        assertThat(Utils.getOrDefault(emptyMap, map1)).isSameAs(map1);

        assertThat(Utils.getOrDefault(map1, map2)).isSameAs(map1).isNotSameAs(map2);
    }

    @Test
    void is_null_or_blank() {
        assertThat(Utils.isNullOrBlank(null)).isTrue();
        assertThat(Utils.isNullOrBlank("")).isTrue();
        assertThat(Utils.isNullOrBlank(" ")).isTrue();
        assertThat(Utils.isNullOrBlank("foo")).isFalse();

        assertThat(Utils.isNotNullOrBlank(null)).isFalse();
        assertThat(Utils.isNotNullOrBlank("")).isFalse();
        assertThat(Utils.isNotNullOrBlank(" ")).isFalse();
        assertThat(Utils.isNotNullOrBlank("foo")).isTrue();
    }

    @Test
    void string_is_null_or_empty() {
        assertThat(Utils.isNullOrEmpty((String) null)).isTrue();
        assertThat(Utils.isNullOrEmpty("")).isTrue();
        assertThat(Utils.isNullOrEmpty(" ")).isFalse();
        assertThat(Utils.isNullOrEmpty("\n")).isFalse();
        assertThat(Utils.isNullOrEmpty("foo")).isFalse();
    }

    @Test
    void string_is_not_null_or_empty() {
        assertThat(Utils.isNotNullOrEmpty(null)).isFalse();
        assertThat(Utils.isNotNullOrEmpty("")).isFalse();
        assertThat(Utils.isNotNullOrEmpty(" ")).isTrue();
        assertThat(Utils.isNotNullOrEmpty("\n")).isTrue();
        assertThat(Utils.isNotNullOrEmpty("foo")).isTrue();
    }

    @Test
    void collection_is_null_or_empty() {
        assertThat(Utils.isNullOrEmpty((Collection<?>) null)).isTrue();
        assertThat(Utils.isNullOrEmpty(emptyList())).isTrue();
        assertThat(Utils.isNullOrEmpty(Collections.singletonList("abc"))).isFalse();
    }

    @Test
    void iterable_is_null_or_empty() {
        assertThat(Utils.isNullOrEmpty((Iterable<?>) null)).isTrue();
        assertThat(Utils.isNullOrEmpty((Iterable<?>) emptyList())).isTrue();
        assertThat(Utils.isNullOrEmpty((Iterable<?>) Collections.singletonList("abc")))
                .isFalse();
    }

    @Test
    void array_is_null_or_empty() {
        // Null array
        assertThat(Utils.isNullOrEmpty((Object[]) null)).isTrue();

        // Empty array
        assertThat(Utils.isNullOrEmpty(new Object[0])).isTrue();

        // Non-empty array with one element
        assertThat(Utils.isNullOrEmpty(new Object[] {"abc"})).isFalse();

        // Non-empty array with multiple elements
        assertThat(Utils.isNullOrEmpty(new Object[] {"a", "b", "c"})).isFalse();

        // Array with a null element (still non-empty)
        assertThat(Utils.isNullOrEmpty(new Object[] {null})).isFalse();

        // Mixed null and non-null elements
        assertThat(Utils.isNullOrEmpty(new Object[] {null, "xyz"})).isFalse();
    }

    @MethodSource
    @ParameterizedTest
    void test_firstNotNull(Object[] values, Object expected) {
        assertThat(Utils.firstNotNull("testParam", values)).isEqualTo(expected);
    }

    static Stream<Arguments> test_firstNotNull() {
        return Stream.of(
                Arguments.of(new Object[] {"first", "second"}, "first"),
                Arguments.of(new Object[] {null, "second"}, "second"),
                Arguments.of(new Object[] {null, null, "third"}, "third"),
                Arguments.of(new Object[] {42, null}, 42),
                Arguments.of(new Object[] {null, true}, true));
    }

    @Test
    void firstNotNull_throwsWhenAllValuesAreNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Utils.firstNotNull("testParam", (Object) null, null))
                .withMessageContaining("At least one of the given 'testParam' values must be not null");
    }

    @Test
    void firstNotNull_throwsWhenValuesArrayIsEmpty() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Utils.firstNotNull("testParam"))
                .withMessageContaining("testParam values cannot be null or empty");
    }
}

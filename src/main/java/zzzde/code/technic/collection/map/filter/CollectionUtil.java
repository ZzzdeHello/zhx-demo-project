package zzzde.code.technic.collection.map.filter;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hesj@zjhcsoft.com
 * @date 2019/9/10 17:37
 */
public class CollectionUtil {

    public static <T> boolean isNotEmpty(final Collection<T> collection) {
        return CollectionUtils.isNotEmpty(collection);
    }

    public static <T> boolean isEmpty(final Collection<T> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    public static <T> int getSize(final Collection<T> collection) {
        return CollectionUtils.isNotEmpty(collection) ? collection.size() : 0;
    }

    public static <T> int getSize(final Collection<T> collection, Predicate<T> filter) {
        return getSize(filterList(collection, filter));
    }

    @SafeVarargs
    public static <T> boolean anyEmpty(final Collection<T>... collections) {
        return Stream.of(collections).anyMatch(CollectionUtils::isEmpty);
    }

    public static boolean allEmpty(final Collection<?>... collections) {
        return Stream.of(collections).allMatch(CollectionUtils::isEmpty);
    }

    @SafeVarargs
    public static <T> boolean allNotEmpty(final Collection<T>... collections) {
        return Stream.of(collections).allMatch(CollectionUtils::isNotEmpty);
    }

    @SafeVarargs
    public static <T> boolean containsAll(final Collection<T> collection, T... ts) {
        return isNotEmpty(collection) && CollectionUtils.containsAll(collection, Stream.of(ts).collect(Collectors.toList()));
    }

    @SafeVarargs
    public static <T> boolean containsAny(final Collection<T> collection, T... ts) {
        return isNotEmpty(collection) && CollectionUtils.containsAny(collection, Stream.of(ts).collect(Collectors.toList()));
    }

    public static <T> boolean contains(final Collection<T> collection, T t) {
        return isNotEmpty(collection) && collection.contains(t);
    }

    @SafeVarargs
    public static <T> boolean containsNone(final Collection<T> collection, T... ts) {
        return !containsAll(collection, ts);
    }

    public static <T> boolean anyMatch(final Collection<T> collection, Predicate<T> filter) {
        return isNotEmpty(collection) && collection.stream().anyMatch(filter);
    }

    @SafeVarargs
    public static <T> boolean anyMatch(Predicate<T> filter, final T... ts) {
        return Stream.of(ts).filter(Objects::nonNull).anyMatch(filter);
    }

    public static <T> boolean allMatch(final Collection<T> collection, Predicate<T> filter) {
        return isNotEmpty(collection) && collection.stream().allMatch(filter);
    }

    public static <T> boolean noneMatch(final Collection<T> collection, Predicate<T> filter) {
        return isEmpty(collection) || collection.stream().noneMatch(filter);
    }

    public static <T> void removeIf(final Collection<T> collection, Predicate<T> filter) {
        if (isNotEmpty(collection)) {
            collection.removeIf(filter);
        }
    }

    public static <T> T findAny(final Collection<T> collection, Predicate<T> filter) {
        if (isNotEmpty(collection)) {
            return collection.stream().filter(Objects::nonNull).filter(filter).findAny().orElse(null);
        }
        return null;
    }

    public static <T> T findFirst(final Collection<T> collection, Predicate<T> filter) {
        if (isNotEmpty(collection)) {
            return collection.stream().filter(Objects::nonNull).filter(filter).findFirst().orElse(null);
        }
        return null;
    }

    public static <T, R> R mapFirst(final Collection<T> collection, Function<? super T, ? extends R> mapper) {
        if (isNotEmpty(collection)) {
            return collection.stream().filter(Objects::nonNull).findFirst().map(mapper).orElse(null);
        }
        return null;
    }

    public static <T, R> R mapFirst(final Collection<T> collection, Predicate<T> filter, Function<? super T, ? extends R> mapper) {
        if (isNotEmpty(collection)) {
            return collection.stream().filter(Objects::nonNull).filter(filter).findFirst().map(mapper).orElse(null);
        }
        return null;
    }

    public static <T> T getFirst(final List<T> list, Predicate<T> filter) {
        if (isNotEmpty(list)) {
            T t = findFirst(list, filter);
            return Objects.nonNull(t) ? t : list.get(0);
        }
        return null;
    }

    public static <T> T getFirst(final List<T> list) {
        if (isNotEmpty(list)) {
            T t = findFirst(list, Objects::nonNull);
            return Objects.nonNull(t) ? t : list.get(0);
        }
        return null;
    }

    public static <T> T getIndex(final List<T> list, int index) {
        if (isNotEmpty(list) && getSize(list) > index) {
            return list.get(index);
        }
        return null;
    }

    public static <T> T findMin(final Collection<T> collection, Comparator<? super T> comparator) {
        return min(collection, comparator).orElse(null);
    }

    public static <T> Optional<T> min(final Collection<T> collection, Comparator<? super T> comparator) {
        if (isNotEmpty(collection)) {
            return collection.stream().filter(Objects::nonNull).min(comparator);
        }
        return Optional.empty();
    }

    public static <T> Optional<T> max(final Collection<T> collection, Comparator<? super T> comparator) {
        if (isNotEmpty(collection)) {
            return collection.stream().filter(Objects::nonNull).max(comparator);
        }
        return Optional.empty();
    }

    public static <T> void forEach(final Collection<T> collection, Consumer<? super T> action) {
        if (isNotEmpty(collection)) {
            collection.stream().filter(Objects::nonNull).forEach(action);
        }
    }

    public static <T> void parallelForEach(final Collection<T> collection, Consumer<? super T> action) {
        if (isNotEmpty(collection)) {
            collection.parallelStream().filter(Objects::nonNull).forEach(action);
        }
    }

    public static <T> void forEach(final Collection<T> collection, Predicate<T> filter, Consumer<? super T> action) {
        if (isNotEmpty(collection)) {
            collection.stream().filter(filter).filter(Objects::nonNull).forEach(action);
        }
    }

    public static <T> void parallelForEach(final Collection<T> collection, Predicate<T> filter, Consumer<? super T> action) {
        if (isNotEmpty(collection)) {
            collection.parallelStream().filter(filter).filter(Objects::nonNull).forEach(action);
        }
    }

    public static <T> Collection<T> addAll(Collection<T> collection, final Collection<? extends T> collect) {
        if (Objects.isNull(collection)) {
            collection = CollectionUtils.emptyCollection();
        }
        if (isNotEmpty(collect)) {
            collection.addAll(collect);
        }
        return collection;
    }

    public static <T> List<T> add(List<T> list, final T t) {
        if (Objects.isNull(list)) {
            list = new ArrayList<>();
        }
        if (Objects.nonNull(t)) {
            list.add(t);
        }
        return list;
    }

    @SafeVarargs
    public static <T> List<T> collectList(final Collection<? extends T>... collections) {
        return Stream.of(collections).filter(CollectionUtils::isNotEmpty)
                .flatMap(Collection::stream).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @SafeVarargs
    public static <T> List<T> collectList(final T... ts) {
        return Stream.of(ts).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static <T, R, A> R collect(final Collection<? extends T> collections, Collector<? super T, A, R> collector) {
        if (isNotEmpty(collections)) {
            return collections.stream().filter(Objects::nonNull).collect(collector);
        }
        return null;
    }

    public static <T> List<T> toList(final T[] ts) {
        return Stream.of(ts).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static <T, R> List<R> toList(final T[] ts, Function<? super T, ? extends R> mapper) {
        return map2List(Stream.of(ts).filter(Objects::nonNull).collect(Collectors.toList()), mapper);
    }

    public static <T> List<T> filterList(final Collection<T> collection, Predicate<T> filter) {
        if (isNotEmpty(collection)) {
            return collection.stream().filter(Objects::nonNull).filter(filter).collect(Collectors.toList());
        }
        return null;
    }

    public static <T, R> List<R> map2List(final Collection<T> collection, Function<? super T, ? extends R> mapper) {
        if (isNotEmpty(collection)) {
            return collection.stream().filter(Objects::nonNull).map(mapper).filter(Objects::nonNull).collect(Collectors.toList());
        }
        return null;
    }

    public static <T, R> List<R> filterMapList(final Collection<T> collection, Predicate<T> filter, Function<? super T, ? extends R> mapper) {
        return map2List(filterList(collection, filter), mapper);
    }

    public static <T, R> List<R> filterFlatMapList(final Collection<T> collection, Predicate<T> filter,
                                                   Function<? super T, ? extends List<? extends R>> mapper) {
        return flatMap2List(filterList(collection, filter), mapper);
    }

    @SafeVarargs
    public static <T, R> List<R> map2List(Function<? super T, ? extends R> mapper, final T... ts) {
        return map2List(collectList(ts), mapper);
    }

    public static <T, R> List<R> mapToList(final Collection<T> collection,
                                           Function<? super T, ? extends T> oldMapper,
                                           Function<? super T, ? extends R> mapper) {
        return collectList(map2List(collection, mapper), map2List(map2List(collection, oldMapper), mapper));
    }

    public static <T, R> List<R> map2List(final Collection<T> collection,
                                          Function<? super T, ? extends R> mapper,
                                          Function<? super R, ? extends R> oldMapper) {
        return collectList(map2List(collection, mapper), map2List(map2List(collection, mapper), oldMapper));
    }

    public static <T, R> List<R> flatMap2List(final Collection<T> collection, Function<? super T, ? extends List<? extends R>> mapper) {
        if (isNotEmpty(collection)) {
            return collection.stream().map(mapper).filter(CollectionUtils::isNotEmpty)
                    .flatMap(Collection::stream).filter(Objects::nonNull).collect(Collectors.toList());
        }
        return null;
    }

    public static <T, R> List<R> flatMap2List(final Collection<T> collection,
                                              Function<? super T, ? extends List<? extends R>> mapper,
                                              Function<? super R, ? extends R> oldMapper) {
        return collectList(flatMap2List(collection, mapper), map2List(flatMap2List(collection, mapper), oldMapper));
    }

    public static <T> Optional<T> reduce(final Collection<T> collection, BinaryOperator<T> accumulator) {
        if (isNotEmpty(collection)) {
            return collection.stream().filter(Objects::nonNull).reduce(accumulator);
        }
        return Optional.empty();
    }

    public static <T> Optional<T> reduce(final Collection<T> collection, Predicate<T> filter, BinaryOperator<T> accumulator) {
        if (isNotEmpty(collection)) {
            return collection.stream().filter(filter).reduce(accumulator);
        }
        return Optional.empty();
    }

    public static <T> List<T> sorted(final Collection<T> collection, Comparator<? super T> comparator) {
        if (isNotEmpty(collection)) {
            return collection.stream().filter(Objects::nonNull).sorted(comparator).collect(Collectors.toList());
        }
        return null;
    }

    public static <T> List<T> reversed(final Collection<T> collection, Comparator<? super T> comparator) {
        if (isNotEmpty(collection)) {
            return collection.stream().filter(Objects::nonNull).sorted(comparator.reversed()).collect(Collectors.toList());
        }
        return null;
    }

    public static <T, K> Map<K, List<T>> groupingBy(final Collection<T> collection, Function<? super T, ? extends K> classifier) {
        return collect(collection, Collectors.groupingBy(classifier));
    }

    public static <T> List<T> distinct(Collection<T> collection) {
        if (isNotEmpty(collection)) {
            return collection.stream().distinct().collect(Collectors.toList());
        }
        return null;
    }

    public static <T, R> List<R> filterMapDuplicates(final Collection<T> collection, Function<? super T, ? extends R> mapper) {
        return filterDuplicates(map2List(collection, mapper));
    }

    /**
     * 提取集合中重复的元素
     *
     * @param list 集合
     * @param <T>  类型
     * @return 返回结果
     */
    public static <T> List<T> filterDuplicates(List<T> list) {
        if (isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
                    .entrySet().stream().filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
                    .map(Map.Entry::getKey).collect(Collectors.toList()); // 转化为 List
        }
        return list;
    }

    public static <T> List<T> subList(List<T> list, int fromIndex, int toIndex) {
        if (isNotEmpty(list)) {
            return list.subList(fromIndex, toIndex);
        }
        return list;
    }

    public static <T> List<T> subList(List<T> list, int toIndex) {
        return subList(list, 0, toIndex);
    }

    public static <T> int count(final Collection<T> collection, Predicate<T> filter) {
        return getSize(filterList(collection, filter));
    }
}

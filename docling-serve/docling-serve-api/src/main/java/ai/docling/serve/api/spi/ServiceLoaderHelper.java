package ai.docling.serve.api.spi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ServiceLoader;

import org.jspecify.annotations.Nullable;

/**
 * Utility wrapper around {@code ServiceLoader.load()}.
 */
public final class ServiceLoaderHelper {
    private ServiceLoaderHelper() {
    }

    /**
     * Load the first available service of a given type.
     *
     * @param clazz the type of service
     * @param <T>   the type of service
     * @return the first service, or {@link Optional#empty()} if none
     */
    public static <T> Optional<T> loadFactory(Class<T> clazz) {
        var factories = loadFactories(clazz, null);
        return factories.isEmpty() ? Optional.empty() : Optional.ofNullable(factories.iterator().next());
    }

    /**
     * Load all the services of a given type.
     *
     * @param clazz the type of service
     * @param <T>   the type of service
     * @return the list of services, empty if none
     */
    public static <T> Collection<T> loadFactories(Class<T> clazz) {
        return loadFactories(clazz, null);
    }

    /**
     * Load all the services of a given type.
     *
     * <p>Utility mechanism around {@code ServiceLoader.load()}</p>
     *
     * <ul>
     *     <li>If classloader is {@code null}, will try {@code ServiceLoader.load(clazz)}</li>
     *     <li>If classloader is not {@code null}, will try {@code ServiceLoader.load(clazz, classloader)}</li>
     *     </ul>
     *
     * <p>If the above return nothing, will fall back to {@code ServiceLoader.load(clazz, $this class loader$)}</p>
     *
     * @param clazz       the type of service
     * @param classLoader the classloader to use, may be null
     * @param <T>         the type of service
     * @return the list of services, empty if none
     */
    public static <T> Collection<T> loadFactories(Class<T> clazz, @Nullable ClassLoader classLoader) {
      var factories = Optional.ofNullable(classLoader)
          .map(cl -> loadAll(ServiceLoader.load(clazz, cl)))
          .orElseGet(() -> loadAll(ServiceLoader.load(clazz)));

      // By default, ServiceLoader.load uses the TCCL, this may not be enough in environment dealing with
      // classloaders differently such as OSGi. So we should try to use the classloader having loaded this
      // class. In OSGi it would be the bundle exposing vert.x and so have access to all its classes.
      var factoriesToReturn =  factories.isEmpty() ?
          loadAll(ServiceLoader.load(clazz, ServiceLoaderHelper.class.getClassLoader())) :
          factories;

      return factoriesToReturn.stream()
          .filter(Objects::nonNull)
          .toList();
    }

    /**
     * Load all the services from a ServiceLoader.
     *
     * @param loader the loader
     * @param <T>    the type of service
     * @return the list of services, empty if none
     */
    private static <T> List<T> loadAll(ServiceLoader<T> loader) {
        List<T> list = new ArrayList<>();
        loader.iterator().forEachRemaining(list::add);

        return list;
    }
}

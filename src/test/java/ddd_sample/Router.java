package ddd_sample;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Entity
public class Router {
    private final RouterType routerType;
    private final RouterId routerId;

    public Router(RouterType routerType, RouterId routerId) {
        this.routerId = routerId;
        this.routerType = routerType;
    }

    public static Predicate<Router> filterRouterByType(RouterType routerType) {
        return routerType.equals(RouterType.CORE) ? isCore(): isEdgge();
    }

    private static Predicate<Router> isCore() {
        return p -> p.getRouterType() == RouterType.CORE;
    }

    private static Predicate<Router> isEdgge() {
        return p -> p.getRouterType() == RouterType.EDGE;
    }

    public static List<Router> retrieveRouter(List<Router> routers,
                                              Predicate<Router> pridicate) {
        return routers.stream()
                .filter(pridicate)
                .collect(Collectors.<Router>toList());
    }

    public RouterType getRouterType() {
        return routerType;
    }
}

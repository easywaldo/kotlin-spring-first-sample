package ddd_sample.entity;

import ddd_sample.vo.RouterId;
import ddd_sample.vo.RouterType;
import ddd_sample.vo.IP;
import ddd_sample.vo.Network;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Entity
public class Router {
    private final RouterType routerType;
    private final RouterId routerId;
    private Switch networkSwitch;

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

    public void addNetworkToSwitch(Network network) {

        this.networkSwitch = networkSwitch.addNetworks(network);
    }
    public Network createNetwork(IP address, String name, int cidr) {
        return new Network(address, name, cidr);
    }
    public List<Network> retrieveNetworks() {
        return networkSwitch.getNetworks();
    }

    @Override
    public String toString() {
        return "Router{" +
                "routerType=" + routerType +
                ", routerId=" + routerId +
                ", networkSwitch=" + networkSwitch +
                '}';
    }
}

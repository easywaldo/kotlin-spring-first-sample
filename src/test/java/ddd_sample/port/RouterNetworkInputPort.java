package ddd_sample.port;

import ddd_sample.entity.Router;
import ddd_sample.service.NetworkOperation;
import ddd_sample.use_case.RouterNetworkOutputPort;
import ddd_sample.use_case.RouterNetworkUseCase;
import ddd_sample.vo.Network;
import ddd_sample.vo.RouterId;

public class RouterNetworkInputPort implements RouterNetworkUseCase {
    private final RouterNetworkOutputPort routerNetworkOutputPort;

    public RouterNetworkInputPort(RouterNetworkOutputPort routerNetworkOutputPort) {
        this.routerNetworkOutputPort = routerNetworkOutputPort;
    }

    @Override
    public Router addNetworkToRouter(RouterId routerId, Network network) {
        var router = fetchRouter(routerId);
        return createNetwork(router, network);
    }

    private Router createNetwork(Router router, Network network) {
        var newRouter = NetworkOperation.createNewNetwork(router, network);
        return persistNetwork(router) ? newRouter : router;
    }

    private boolean persistNetwork(Router router) {
        return routerNetworkOutputPort.persistRouter(router);
    }

    private Router fetchRouter(RouterId routerId) {
        return routerNetworkOutputPort.fetchRouterById(routerId);
    }
}

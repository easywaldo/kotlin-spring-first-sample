package ddd_sample.port;

import ddd_sample.Router;
import ddd_sample.use_case.RouterViewUseCase;

import java.util.List;
import java.util.function.Predicate;

public class RouterViewInputPort implements RouterViewUseCase {
    private RouterViewOutputPort routerViewOutputPort;

    public RouterViewInputPort(RouterViewOutputPort routerViewOutputPort) {
        this.routerViewOutputPort = routerViewOutputPort;
    }

    @Override
    public List<Router> getRouters(Predicate<Router> filter) {
        return null;
    }
}

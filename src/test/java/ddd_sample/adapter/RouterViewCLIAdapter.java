package ddd_sample.adapter;

import ddd_sample.entity.Router;
import ddd_sample.vo.RouterType;
import ddd_sample.port.RouterViewInputPort;
import ddd_sample.use_case.RouterViewUseCase;

import java.util.List;

public class RouterViewCLIAdapter {
    RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter() {
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Router.filterRouterByType(RouterType.valueOf(type))
        );
    }

    private void setAdapters() {
        this.routerViewUseCase = new RouterViewInputPort(new RouterViewFileAdapter());
    }
}

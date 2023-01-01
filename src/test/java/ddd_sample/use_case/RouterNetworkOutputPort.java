package ddd_sample.use_case;

import ddd_sample.entity.Router;
import ddd_sample.vo.RouterId;

public interface RouterNetworkOutputPort {
    Router fetchRouterById(RouterId routerId);
    boolean persistRouter(Router router);
}

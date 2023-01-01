package ddd_sample.use_case;

import ddd_sample.entity.Router;
import ddd_sample.vo.Network;
import ddd_sample.vo.RouterId;

public interface RouterNetworkUseCase {
    Router addNetworkToRouter(RouterId routerId, Network network);
}

package ddd_sample.port;

import ddd_sample.Router;

import java.util.List;

public interface RouterViewOutputPort {
    List<Router> fetchRouters();
}

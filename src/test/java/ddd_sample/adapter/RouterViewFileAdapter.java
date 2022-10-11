package ddd_sample.adapter;

import ddd_sample.Router;
import ddd_sample.RouterId;
import ddd_sample.RouterType;
import ddd_sample.port.RouterViewOutputPort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RouterViewFileAdapter implements RouterViewOutputPort {
    @Override
    public List<Router> fetchRouters() {
        return readFileAsString();
    }

    private static List<Router> readFileAsString() {
        List<Router> routers = new ArrayList<>();
        try (Stream<String> stream = new BufferedReader(new InputStreamReader(
                RouterViewFileAdapter.class.getClassLoader()
                        .getResourceAsStream("routers.txt"))).lines()) {
            stream.forEach(line -> {
                String[] routerEntity = line.split(";");
                var id = routerEntity[0];
                var type = routerEntity[1];
                Router router = new Router(RouterType.valueOf(type), RouterId.withId("12345678910"));
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return routers;
    }
}

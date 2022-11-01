package ddd_sample.service;

import ddd_sample.entity.Router;
import ddd_sample.vo.IP;
import ddd_sample.vo.Network;

public class NetworkOperation {
    final private int MINIMUM_ALLOWED_CIDR = 8;
    public void createNewNetwork(Router router, IP address, String name, int cidr) {
        if(cidr < MINIMUM_ALLOWED_CIDR) {
            throw new IllegalArgumentException("CIDR is below" + MINIMUM_ALLOWED_CIDR);
        }
        if (isNetworkAvaliable(router, address, cidr)) {
            throw new IllegalArgumentException("Address already exists");
        }
    }

    private boolean isNetworkAvaliable(Router router, IP address, int cidr) {
        var availability = true;
        for (Network network: router.retrieveNetworks()) {
            if (network.getAddress().equals(address) && network.getCidr() == cidr) {
                availability = false;
                break;
            }
        }
        return availability;
    }
}

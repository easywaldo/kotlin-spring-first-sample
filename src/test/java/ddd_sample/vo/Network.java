package ddd_sample.vo;

import org.bouncycastle.util.IPAddress;

public class Network {
    private final IPAddress address;
    private final String name;
    private final int cidr;

    public Network(IPAddress address, String name, int cidr) {
        if (cidr < 1 || cidr > 32) {
            throw new IllegalArgumentException("Invalid CIDR value");
        }
        this.address = address;
        this.cidr = cidr;
        this.name = name;
    }
}

package ddd_sample.entity;

import ddd_sample.vo.SwitchId;
import ddd_sample.vo.SwitchType;
import ddd_sample.vo.IP;
import ddd_sample.vo.Network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Switch {
    private SwitchType switchType;
    private SwitchId switchId;
    private List<Network> networks;
    private IP address;

    public Switch(SwitchType switchType, SwitchId switchId, List<Network> networks, IP address) {
        this.switchId = switchId;
        this.switchType = switchType;
        this.networks = networks;
        this.address = address;
    }

    public Switch addNetworks(Network network) {
        var networks = new ArrayList<>(Arrays.asList(network));
        networks.add(network);
        return new Switch(this.switchType, this.switchId, networks, this.address);
    }

    public List<Network> getNetworks() {
        return networks;
    }
}

package ddd_sample;

public class Activity {
    private String description;
    private String srcHost;
    private String dstHost;

    public Activity(String description, String srcHost, String dstHost) {
        this.description = description;
        this.srcHost = srcHost;
        this.dstHost = dstHost;
    }

    public String retrieveSrcHost() {
        return this.srcHost;
    }

}

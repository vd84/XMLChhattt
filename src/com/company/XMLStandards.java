package com.company;

public class XMLStandards {
    private String type;
    private String version;
    private String host;
    private String encoding;

    public XMLStandards() {
        this.type = "CTTP";
        this.version = "1.0";
        this.host = "unknown";
        this.encoding = "UTF-8";
    }


    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public String getHost() {
        return host;
    }

    public String getEncoding() {
        return encoding;
    }
}

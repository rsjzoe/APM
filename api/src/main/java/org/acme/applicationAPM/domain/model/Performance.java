package org.acme.applicationAPM.domain.model;

public class Performance {
    private int responseTimeMs; // en ms 2ms par ex

    public Performance() {
    }

    public Performance(int responseTimeMs) {
        this.responseTimeMs = responseTimeMs;
    }

    public int getResponseTimeMs() {
        return responseTimeMs;
    }

    public void setResponseTimeMs(int responseTimeMs) {
        this.responseTimeMs = responseTimeMs;
    }

    @Override
    public String toString() {
        return "Performance{" +
                "responseTimeMs=" + responseTimeMs +
                '}';
    }
}

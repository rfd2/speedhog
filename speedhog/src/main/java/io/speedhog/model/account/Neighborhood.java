package io.speedhog.model.account;

import java.time.LocalDateTime;

public class Neighborhood {

    private String neighborhoodId;
    private String accountId;
    private String name;
    private String geohash;
    private LocalDateTime created;

    public Neighborhood() {}

    public Neighborhood(
        String neighborhoodId,
        String accountId,
        String name,
        String geohash,
        LocalDateTime created
    ) {
        this.neighborhoodId = neighborhoodId;
        this.accountId = accountId;
        this.name = name;
        this.geohash = geohash;
        this.created = created;
    }

    public String getNeighborhoodId() {
        return neighborhoodId;
    }

    public void setNeighborhoodId(String neighborhoodId) {
        this.neighborhoodId = neighborhoodId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}

package io.speedhog.model.speedhog;

import java.time.LocalDateTime;

public class Speedhog {

    private Integer speedhogId;
    private String speedhogIdExt;
    private String accountId;
    private String neighborhoodId;
    private String speedhogName;
    private Short speedLimit;
    private Short offenseThreshold;
    private SpeedUnit unit;
    private String geohash;
    private String streetAddress;
    private String captureVersion;
    private String sysVersion;
    private boolean showLocation;
    private boolean active;
    private LocalDateTime created;

    public enum SpeedUnit {
        mph,
        kmh,
    }

    public Speedhog() {}

    public Speedhog(
        Integer speedhogId,
        String speedhogIdExt,
        String accountId,
        String neighborhoodId,
        String speedhogName,
        Short speedLimit,
        Short offenseThreshold,
        SpeedUnit unit,
        String geohash,
        String streetAddress,
        String captureVersion,
        String sysVersion,
        boolean showLocation,
        boolean active,
        LocalDateTime created
    ) {
        this.speedhogId = speedhogId;
        this.speedhogIdExt = speedhogIdExt;
        this.accountId = accountId;
        this.neighborhoodId = neighborhoodId;
        this.speedhogName = speedhogName;
        this.speedLimit = speedLimit;
        this.offenseThreshold = offenseThreshold;
        this.unit = unit;
        this.geohash = geohash;
        this.streetAddress = streetAddress;
        this.captureVersion = captureVersion;
        this.sysVersion = sysVersion;
        this.showLocation = showLocation;
        this.active = active;
        this.created = created;
    }

    public Integer getSpeedhogId() {
        return speedhogId;
    }

    public void setSpeedhogId(Integer speedhogId) {
        this.speedhogId = speedhogId;
    }

    public String getSpeedhogIdExt() {
        return speedhogIdExt;
    }

    public void setSpeedhogIdExt(String speedhogIdExt) {
        this.speedhogIdExt = speedhogIdExt;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getNeighborhoodId() {
        return neighborhoodId;
    }

    public void setNeighborhoodId(String neighborhoodId) {
        this.neighborhoodId = neighborhoodId;
    }

    public String getSpeedhogName() {
        return speedhogName;
    }

    public void setSpeedhogName(String speedhogName) {
        this.speedhogName = speedhogName;
    }

    public Short getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(Short speedLimit) {
        this.speedLimit = speedLimit;
    }

    public Short getOffenseThreshold() {
        return offenseThreshold;
    }

    public void setOffenseThreshold(Short offenseThreshold) {
        this.offenseThreshold = offenseThreshold;
    }

    public SpeedUnit getUnit() {
        return unit;
    }

    public void setUnit(SpeedUnit unit) {
        this.unit = unit;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCaptureVersion() {
        return captureVersion;
    }

    public void setCaptureVersion(String captureVersion) {
        this.captureVersion = captureVersion;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    public boolean isShowLocation() {
        return showLocation;
    }

    public void setShowLocation(boolean showLocation) {
        this.showLocation = showLocation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}

package io.speedhog.model.offense;

import io.speedhog.model.event.Event.Direction;
import java.time.LocalDateTime;

public class Offense {

    private Long offenseId;
    private String offenseIdExt;
    private String vehicleId;
    private Long eventId;
    private Integer speedhogId;
    private Short speed;
    private Direction direction;
    private LocalDateTime captured;

    public Offense() {}

    public Offense(
        Long offenseId,
        String offenseIdExt,
        String vehicleId,
        Long eventId,
        Integer speedhogId,
        Short speed,
        Direction direction,
        LocalDateTime captured
    ) {
        this.offenseId = offenseId;
        this.offenseIdExt = offenseIdExt;
        this.vehicleId = vehicleId;
        this.eventId = eventId;
        this.speedhogId = speedhogId;
        this.speed = speed;
        this.direction = direction;
        this.captured = captured;
    }

    public Long getOffenseId() {
        return offenseId;
    }

    public void setOffenseId(Long offenseId) {
        this.offenseId = offenseId;
    }

    public String getOffenseIdExt() {
        return offenseIdExt;
    }

    public void setOffenseIdExt(String offenseIdExt) {
        this.offenseIdExt = offenseIdExt;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Integer getSpeedhogId() {
        return speedhogId;
    }

    public void setSpeedhogId(Integer speedhogId) {
        this.speedhogId = speedhogId;
    }

    public Short getSpeed() {
        return speed;
    }

    public void setSpeed(Short speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public LocalDateTime getCaptured() {
        return captured;
    }

    public void setCaptured(LocalDateTime captured) {
        this.captured = captured;
    }
}

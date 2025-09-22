package io.speedhog.model.event;

import java.time.LocalDateTime;

public class Event {

    private Long eventId;
    private Integer speedhogId;
    private Short speed;
    private Short exceedsThresholdBy;
    private Direction direction;
    private boolean hasImage;
    private LocalDateTime captured;
    private LocalDateTime inserted;

    public enum Direction {
        north,
        south,
        east,
        west,
    }

    public Event() {}

    public Event(
        Long eventId,
        Integer speedhogId,
        Short speed,
        Short exceedsThresholdBy,
        Direction direction,
        boolean hasImage,
        LocalDateTime captured,
        LocalDateTime inserted
    ) {
        this.eventId = eventId;
        this.speedhogId = speedhogId;
        this.speed = speed;
        this.exceedsThresholdBy = exceedsThresholdBy;
        this.direction = direction;
        this.hasImage = hasImage;
        this.captured = captured;
        this.inserted = inserted;
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

    public Short getExceedsThresholdBy() {
        return exceedsThresholdBy;
    }

    public void setExceedsThresholdBy(Short exceedsThresholdBy) {
        this.exceedsThresholdBy = exceedsThresholdBy;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public LocalDateTime getCaptured() {
        return captured;
    }

    public void setCaptured(LocalDateTime captured) {
        this.captured = captured;
    }

    public LocalDateTime getInserted() {
        return inserted;
    }

    public void setInserted(LocalDateTime inserted) {
        this.inserted = inserted;
    }
}

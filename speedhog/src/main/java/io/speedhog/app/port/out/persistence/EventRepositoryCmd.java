package io.speedhog.app.port.out.persistence;

public interface EventRepositoryCmd {
    public void save(String id, String name);
}

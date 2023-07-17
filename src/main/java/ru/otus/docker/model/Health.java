package ru.otus.docker.model;

public record Health (String status) {
    @Override
    public String status() {
        return status;
    }
}

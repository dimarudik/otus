package ru.otus.docker.model;

import java.net.InetAddress;

public record InternetAddress (InetAddress inetAddress, String hostAddress) {
    @Override
    public InetAddress inetAddress() {
        return inetAddress;
    }

    @Override
    public String hostAddress() {
        return hostAddress;
    }
}

package com.haiprj.games.superknight.actions.interfaces;

public interface ActionListener {

    default String getAction() {
        return this.getClass().getName().split("Listener")[0];
    }
}

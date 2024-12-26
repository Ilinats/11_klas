package org.elsys.cardgame.operations;

import org.elsys.cardgame.api.Operation;

public class OperationImpl implements Operation {
    private final String name;
    private final Runnable action;

    public OperationImpl(String name, Runnable action) {
        this.name = name;
        this.action = action;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        action.run();
    }
}

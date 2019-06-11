package com.util;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class FilterTask<F extends E, E> extends RecursiveTask<List<E>> {

    private transient List<E> entities;
    private Class<F> filterBy;

    FilterTask(List<E> entities, Class<F> filterBy) {
        this.entities = entities;
        this.filterBy = filterBy;
    }

    @Override
    protected List<E> compute() {
        return entities.stream()
                .filter(microphone -> filterBy.isInstance(microphone))
                .collect(Collectors.toList());
    }
}

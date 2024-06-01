package com.otters.admissionsbackend.utils;

public interface IFetchCommand<T, U> {
    public void fetch(T original, U request);
}

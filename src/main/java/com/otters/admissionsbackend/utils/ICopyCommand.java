package com.otters.admissionsbackend.utils;

public interface ICopyCommand<T> {
    public void copy(T copy, T original);
}

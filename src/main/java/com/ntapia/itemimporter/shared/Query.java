package com.ntapia.itemimporter.shared;

public interface Query<T, V> {

    V handle(T query);
}

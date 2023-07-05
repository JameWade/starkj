package com.starknet.stark.provider;

import java.util.concurrent.CompletableFuture;

public interface Request<T> {
    public T send();
    public CompletableFuture<T> sendAsync();
}

package com.github.victorskg.common.inbound;

public interface DeleteIn<R, I> {

    R delete(final I identifier);

}

package com.github.victorskg.common.inbound;

public interface GetIn<R, I> {
    R get(final I identifier);
}

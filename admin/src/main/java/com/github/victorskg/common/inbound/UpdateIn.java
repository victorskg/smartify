package com.github.victorskg.common.inbound;

import java.util.UUID;

public interface UpdateIn<R, P> {

    R update(final UUID uuid, final P parameter);

}

package com.jortega.levelup.auth.port;

import java.util.UUID;

public interface CurrentUserProvider {
    UUID idOrThrow();
}

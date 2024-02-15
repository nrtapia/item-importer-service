package com.ntapia.itemimporter.domain;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(String id);
}

package com.ntapia.itemimporter.application.user.search;

import com.ntapia.itemimporter.domain.User;
import com.ntapia.itemimporter.domain.UserRepository;
import com.ntapia.itemimporter.application.user.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FindUserSearchHandlerImpl implements FindUserSearchHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindUserSearchHandlerImpl.class);

    private final UserRepository userRepository;

    public FindUserSearchHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User handle(FindUserSearchQuery query) {
        LOGGER.info("User searching for: {}", query);
        return userRepository.findById(query.id()).orElseThrow(UserNotFoundException::new);
    }
}
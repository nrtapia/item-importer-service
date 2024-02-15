package com.ntapia.itemimporter.infrastructure.client.user;

import com.ntapia.itemimporter.domain.User;
import com.ntapia.itemimporter.domain.UserRepository;
import com.ntapia.itemimporter.infrastructure.config.MeliRestClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryRestClientImpl implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryRestClientImpl.class);

    private final MeliRestClient meliRestClient;

    public UserRepositoryRestClientImpl(MeliRestClient meliRestClient) {
        this.meliRestClient = meliRestClient;
    }

    @Override
    public Optional<User> findById(String id) {
        try {
            var userResponse = meliRestClient.getUser(id);
            return Optional.of(UserMapper.INSTANCE.responseToDomain(userResponse));
        } catch (FeignException.NotFound ignored) {
            return Optional.empty();
        } catch (FeignException fex) {
            LOGGER.error("Failed to find user: {} {}", id, fex.getMessage(), fex);
            throw new FindUserException(String.format("Id: %s - %s", id, fex.getMessage()), fex);
        }
    }
}

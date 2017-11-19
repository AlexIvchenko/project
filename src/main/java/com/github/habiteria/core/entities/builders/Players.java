package com.github.habiteria.core.entities.builders;

import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.imps.PlayerImpl;

/**
 * @author Alex Ivchenko
 */
public class Players {
    public static PlayerImpl forUser(User user) {
        return new Builder().forUser(user);
    }

    private static class Builder implements UserStageBuilder {
        @Override
        public PlayerImpl forUser(User user) {
            PlayerImpl player = new PlayerImpl();
            player.setHealth(100);
            player.setPoints(0);
            player.setUser(user);
            return player;
        }
    }

    public interface UserStageBuilder {
        PlayerImpl forUser(User user);
    }
}

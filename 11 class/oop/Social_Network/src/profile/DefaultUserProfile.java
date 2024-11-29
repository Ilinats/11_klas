package profile;

import java.util.Collection;
import java.util.List;

public class DefaultUserProfile implements UserProfile {
    private String username;
    private Collection<Interest> interests;
    private Collection<UserProfile> friends;

    public DefaultUserProfile(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<Interest> getInterests() {
        return interests;
    }

    @Override
    public boolean addInterest(Interest interest) {
        return interests.add(interest);
    }

    @Override
    public boolean removeInterest(Interest interest) {
        return interests.remove(interest);
    }

    @Override
    public Collection<UserProfile> getFriends() {
        return friends;
    }

    @Override
    public boolean addFriend(UserProfile userProfile) {
        userProfile.addFriend(this);
        return friends.add(userProfile);
    }

    @Override
    public boolean unfriend(UserProfile userProfile) {
        userProfile.unfriend(this);
        return friends.remove(userProfile);
    }

    @Override
    public boolean isFriend(UserProfile userProfile) {
        return friends.contains(userProfile);
    }
}

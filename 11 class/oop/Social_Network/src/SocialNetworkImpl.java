import post.Post;
import post.SocialFeedPost;
import profile.UserProfile;

import java.util.*;
import java.util.stream.Collectors;

public class SocialNetworkImpl implements SocialNetwork{
    private Set<UserProfile> userProfiles;
    private Collection<Post> posts;

    public SocialNetworkImpl() {
        userProfiles = new HashSet<>();
        posts = new ArrayList<>();
    }

    @Override
    public void registerUser(UserProfile userProfile) throws UserRegistrationException {
        if(userProfiles.contains(userProfile)){
            throw new UserRegistrationException("User already registered");
        }

        if(userProfile == null){
            throw new IllegalArgumentException("User profile is null");
        }
        userProfiles.add(userProfile);
    }

    @Override
    public Set<UserProfile> getAllUsers() {
        return userProfiles;
    }

    @Override
    public Post post(UserProfile userProfile, String content) throws UserRegistrationException {
        if(userProfile == null){
            throw new IllegalArgumentException("User profile is null");
        }

        if(content == null || content.isEmpty()){
            throw new IllegalArgumentException("Content is null or empty");
        }

        if(!userProfiles.contains(userProfile)){
            throw new UserRegistrationException("User profile is not registered");
        }

        Post post = new SocialFeedPost(userProfile, content);
        posts.add(post);
        return post;
    }

    @Override
    public Collection<Post> getPosts() {
        return posts;
    }

    @Override
    public Set<UserProfile> getReachedUsers(Post post) {
        Set<UserProfile> users = post.getAllReactions().values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
        return users;
    }

    @Override
    public Set<UserProfile> getMutualFriends(UserProfile userProfile1, UserProfile userProfile2) throws UserRegistrationException {
        if(userProfile1 == null || userProfile2 == null){
            throw new IllegalArgumentException("User profile is null");
        }

        if(!userProfiles.contains(userProfile1) || !userProfiles.contains(userProfile2)){
            throw new UserRegistrationException("User profile is not registered");
        }

        Collection<UserProfile> friends1 = userProfile1.getFriends();
        Collection<UserProfile> friends2 = userProfile2.getFriends();

        Set<UserProfile> mutualFriends = friends1.stream().filter(friends2::contains).collect(Collectors.toSet());
        return mutualFriends;
    }

    @Override
    public SortedSet<UserProfile> getAllProfilesSortedByFriendsCount() {
        return new TreeSet<>(
                Comparator.comparingInt((UserProfile user) -> user.getFriends().size())
                        .thenComparing(UserProfile::getUsername)
        );
    }
}

package post;

import profile.UserProfile;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class SocialFeedPost implements Post {
    private UUID id;
    private UserProfile author;
    private LocalDateTime publishedOn;
    private String content;
    private Map<ReactionType, Set<UserProfile>> reactions;

    public SocialFeedPost(UserProfile author, String content) {
        this.id = UUID.fromString(UUID.randomUUID().toString());
        this.author = author;
        this.publishedOn = LocalDateTime.now();
        this.content = content;
    }

    @Override
    public String getUniqueId() {
        return id.toString();
    }

    @Override
    public UserProfile getAuthor() {
        return author;
    }

    @Override
    public LocalDateTime getPublishedOn() {
        return publishedOn;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public boolean addReaction(UserProfile userProfile, ReactionType reactionType) {
        return reactions.computeIfAbsent(reactionType, k -> Set.of()).add(userProfile);
    }

    @Override
    public boolean removeReaction(UserProfile userProfile) {
        return reactions.values().stream().anyMatch(set -> set.remove(userProfile));
    }

    @Override
    public Map<ReactionType, Set<UserProfile>> getAllReactions() {
        return reactions;
    }

    @Override
    public int getReactionCount(ReactionType reactionType) {
        return reactions.getOrDefault(reactionType, Set.of()).size();
    }

    @Override
    public int totalReactionsCount() {
        return reactions.values().stream().mapToInt(Set::size).sum();
    }
}

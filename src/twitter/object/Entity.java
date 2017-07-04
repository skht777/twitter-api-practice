package twitter.object;

import org.json.JSONObject;

import java.util.List;

/**
 * @author skht777
 */
public class Entity {
	private final List<TaggedEntity> hashtags;
	private final List<TaggedEntity> symboles;
	private final List<URLEntity> urls;
	private final List<MentionEntity> userMentions;
	private final List<MediaEntity> media;

	static Entity createEntity(JSONObject o) {
		return o.has("entities") ? new Entity(o.getJSONObject("entities")) : null;
	}

	private Entity(JSONObject o) {
		hashtags = JSONUtil.createAnyEntities(TaggedEntity::new, o, "hashtags");
		symboles = JSONUtil.createAnyEntities(TaggedEntity::new, o, "symbols");
		urls = JSONUtil.createAnyEntities(URLEntity::new, o, "urls");
		userMentions = JSONUtil.createAnyEntities(MentionEntity::new, o, "user_mentions");
		media = JSONUtil.createAnyEntities(MediaEntity::new, o, "media");
	}

	public List<TaggedEntity> getHashtags() {
		return hashtags;
	}

	public List<TaggedEntity> getSymboles() {
		return symboles;
	}

	public List<URLEntity> getUrls() {
		return urls;
	}

	public List<MentionEntity> getUserMentions() {
		return userMentions;
	}

	public List<MediaEntity> getMedia() {
		return media;
	}
}

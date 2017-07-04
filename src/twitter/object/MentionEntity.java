package twitter.object;

import org.json.JSONObject;

/**
 * @author skht777
 */
public class MentionEntity {
	private final UserIdentity user;
	private final Index indices;

	MentionEntity(JSONObject o) {
		user = new UserIdentity(o);
		indices = Index.createEntity(o);
	}

	public long getId() {
		return user.getId();
	}

	public String getScreenName() {
		return user.getScreenName();
	}

	public String getName() {
		return user.getName();
	}

	public Index getIndices() {
		return indices;
	}
}

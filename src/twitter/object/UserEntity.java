package twitter.object;

import org.json.JSONObject;

import java.util.List;

/**
 * @author skht777
 */
public class UserEntity {
	private final List<URLEntity> url;
	private final List<URLEntity> description;

	static UserEntity createEntity(JSONObject o) {
		return o == null ? null : new UserEntity(o.getJSONObject("entities"));
	}

	private UserEntity(JSONObject o) {
		url = JSONUtil.createAnyEntities(URLEntity::new, o.getJSONObject("url").getJSONArray("urls"));
		description = JSONUtil.createAnyEntities(URLEntity::new, o.getJSONObject("description").getJSONArray("urls"));
	}

	public List<URLEntity> getURL() {
		return url;
	}

	public List<URLEntity> getDescription() {
		return description;
	}
}

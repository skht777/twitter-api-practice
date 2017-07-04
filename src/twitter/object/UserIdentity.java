package twitter.object;

import org.json.JSONObject;

/**
 * @author skht777
 */
class UserIdentity {
	private final long id;
	private final String sname;
	private final String name;

	UserIdentity(JSONObject o) {
		id = o.getLong("id");
		sname = o.optString("screen_name", null);
		name = o.optString("name", null);
	}

	public long getId() {
		return id;
	}

	public String getScreenName() {
		return sname;
	}

	public String getName() {
		return name;
	}
}

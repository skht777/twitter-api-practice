package twitter.object;

import org.json.JSONObject;

/**
 * @author skht777
 */
public class TaggedEntity {
	private final String text;
	private final Index indices;

	TaggedEntity(JSONObject o) {
		text = o.getString("text");
		indices = Index.createEntity(o);
	}

	public String getText() {
		return text;
	}

	public Index getIndices() {
		return indices;
	}
}

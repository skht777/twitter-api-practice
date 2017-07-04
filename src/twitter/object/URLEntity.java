package twitter.object;

import org.json.JSONObject;

import java.net.URL;

/**
 * @author skht777
 */
public class URLEntity {
	private final URL url;
	private final String durl;
	private final URL exurl;
	private final Index indices;

	URLEntity(JSONObject o) {
		url = JSONUtil.strToURL(o.getString("url"));
		durl = o.getString("display_url");
		exurl = JSONUtil.strToURL(o.getString("expanded_url"));
		indices = Index.createEntity(o);
	}

	public URL getURL() {
		return url;
	}

	public String getDisplayURL() {
		return durl;
	}

	public URL getExpandedURL() {
		return exurl;
	}

	public Index getIndices() {
		return indices;
	}
}

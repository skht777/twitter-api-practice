package twitter.object;

import org.json.JSONObject;

import java.net.URL;

/**
 * @author skht777
 */
public class URLPair {
	private final URL url;
	private final URL surl;

	static URLPair createPair(JSONObject o, String name) {
		return o.has(name) ? new URLPair(o, name) : null;
	}

	private URLPair(JSONObject o, String name) {
		url = JSONUtil.strToURL(o.optString(name));
		surl = JSONUtil.strToURL(o.optString(name + "_https"));
	}

	public URL getHttp() {
		return url;
	}

	public URL getHttps() {
		return surl;
	}
}

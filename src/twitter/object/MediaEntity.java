package twitter.object;

import org.json.JSONObject;

import java.net.URL;
import java.util.Map;

/**
 * @author skht777
 */
public class MediaEntity {
	private final long id;
	private final URLPair url;
	private final URLEntity ue;
	private final Map<SizeEntity, MediaSize> sizes;
	private final String type;
	private final long ssid;
	private final long suid;
	private final String alt;

	public enum SizeEntity {
		MEDIUM, THUMB, SMALL, LARGE;

		private URL joinURL(URL url) {
			return this == MEDIUM ? url :
					JSONUtil.strToURL(url.toExternalForm() + ":" + name().toLowerCase());
		}
	}

	MediaEntity(JSONObject o) {
		id = o.getLong("id");
		url = URLPair.createPair(o, "media_url");
		ue = new URLEntity(o);
		sizes = MediaSize.createSizeMap(o);
		type = o.getString("type");
		ssid = o.getLong("source_status_id");
		suid = o.getLong("source_user_id");
		alt = o.optString("ext_alt_text", null);
	}

	public long getId() {
		return id;
	}

	public URL getMediaURL(SizeEntity size) {
		return size.joinURL(url.getHttp());
	}

	public URL getMediaURL() {
		return getMediaURL(SizeEntity.MEDIUM);
	}

	public URL getMediaSecureURL(SizeEntity size) {
		return size.joinURL(url.getHttps());
	}

	public URL getMediaSecureURL() {
		return getMediaSecureURL(SizeEntity.MEDIUM);
	}

	public URL getURL() {
		return ue.getURL();
	}

	public String getDisplayURL() {
		return ue.getDisplayURL();
	}

	public URL getExpadedURL() {
		return ue.getExpandedURL();
	}

	public MediaSize getSize(SizeEntity size) {
		return sizes.get(size);
	}

	public MediaSize getSize() {
		return getSize(SizeEntity.MEDIUM);
	}

	public String getType() {
		return type;
	}

	public Index getIndices() {
		return ue.getIndices();
	}

	public long getSourceStatusId() {
		return ssid;
	}

	public long getSourceUserId() {
		return suid;
	}

	public String getExtAltText() {
		return alt;
	}
}

package twitter;

import twitter.object.JSONUtil;
import twitter.object.TimeLineEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author skht777
 */
public class API {
	private static final String BASE_URL = "https://api.twitter.com/";
	private TwitterToken token;

	public static class APIFactory {
		private API api;

		public APIFactory(TwitterToken token) {
			api = new API(token);
		}

		public API getInstance() {
			return api;
		}
	}

	private API(TwitterToken token) {
		this.token = token;
	}

	private String sendRequest(String url, String method, Map<String, String> data) throws IOException {
		return TwitterUtil.sendRequest(token, BASE_URL + url, method, data);
	}

	public List<TimeLineEntity> updateStatusWithQuote(String text, String link) throws IOException {
		Map<String, String> data = new TreeMap<>();
		data.put("status", text);
		data.put("trim_user", "1");
		if (link != null) {
			data.put("attachment_url", link);
		}
		return JSONUtil.createTimeLineEntity(sendRequest("1.1/statuses/update.json", "POST", data));
	}

	public List<TimeLineEntity> updateStatus(String text) throws IOException {
		return updateStatusWithQuote(text, null);
	}

	public String getHomeTimeline() throws IOException {
		Map<String, String> data = new TreeMap<>();
		data.put("count", "1");
		//data.put("trim_user", "1");
		return sendRequest("1.1/statuses/home_timeline.json", "GET", data);
	}
}
package twitter.object;

import org.json.JSONObject;

/**
 * @author skht777
 */
public class TimeLineEntity {
	private final Tweet tweet;
	private final Tweet retweetedStatus;
	private final Tweet quotedStatus;

	TimeLineEntity(JSONObject o) {
		tweet = Tweet.createTweet(o);
		retweetedStatus = Tweet.createTweet(o.optJSONObject("retweeted_status"));
		quotedStatus = Tweet.createTweet(o.optJSONObject("quoted_status"));
	}

	public boolean existRetweetedStatus() {
		return retweetedStatus != null;
	}

	public boolean existQuotedStatus() {
		return quotedStatus != null;
	}

	public Tweet getTweet() {
		return tweet;
	}

	public Tweet getRetweetedStatus() {
		return retweetedStatus;
	}

	public Tweet getQuotedStatus() {
		return quotedStatus;
	}
}

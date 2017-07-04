package twitter.object;

import org.json.JSONObject;

import java.util.Date;

/**
 * @author skht777
 */
public class Tweet {
	private final Date createdAt;
	private final long id;
	private final String text;
	private final boolean truncated;
	private final Entity entities;
	private final String source;
	private final long rsid;
	private final long ruid;
	private final String rusname;
	private final User user;
	private final CoordinateEntitiy geo;
	private final CoordinateEntitiy coordinates;
	private final PlaceEntity place;
	private final boolean quoted;
	//private final UserIdentity contributors;
	private final int rtCount;
	private final int favCount;
	private final boolean rted;
	private final boolean faved;
	private final String lang;

	static Tweet createTweet(JSONObject o) {
		return o == null ? null : new Tweet(o);
	}

	Tweet(JSONObject o) {
		createdAt = JSONUtil.parseDateTime(o.getString("created_at"));
		id = o.getLong("id");
		text = o.getString("text");
		truncated = o.getBoolean("truncated");
		entities = Entity.createEntity(o);
		source = o.getString("source");
		rsid = o.optLong("in_reply_to_status_id", -1);
		ruid = o.optLong("in_reply_to_user_id", -1);
		rusname = o.optString("in_reply_to_screen_name", null);
		user = new User(o);
		geo = CoordinateEntitiy.createEntity(o, "geo");
		coordinates = CoordinateEntitiy.createEntity(o, "coordinates");
		place = PlaceEntity.createEntity(o);
		quoted = o.getBoolean("is_quote_status");
		rtCount = o.getInt("retweet_count");
		favCount = o.getInt("favorite_count");
		rted = o.getBoolean("retweeted");
		faved = o.getBoolean("favorited");
		lang = o.optString("lang");
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public boolean isTruncated() {
		return truncated;
	}

	public Entity getEntities() {
		return entities;
	}

	public String getSource() {
		return source;
	}

	public long getInReplyToStatusId() {
		return rsid;
	}

	public long getInReplyToUserId() {
		return ruid;
	}

	public String getInReplyToScreenName() {
		return rusname;
	}

	public User getUser() {
		return user;
	}

	public CoordinateEntitiy getGeo() {
		return geo;
	}

	public CoordinateEntitiy getCoordinates() {
		return coordinates;
	}

	public PlaceEntity getPlace() {
		return place;
	}

	public boolean isQuoted() {
		return quoted;
	}

	public int getRetweetCount() {
		return rtCount;
	}

	public int getFavoriteCount() {
		return favCount;
	}

	public boolean isRetweeted() {
		return rted;
	}

	public boolean isFavorited() {
		return faved;
	}

	public String getLang() {
		return lang;
	}
}

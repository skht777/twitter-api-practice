package twitter.object;

import org.json.JSONObject;

import java.net.URL;
import java.util.Date;

/**
 * @author skht777
 */
public class User {
	private final UserIdentity user;
	private final String location;
	private final String description;
	private final URL url;
	private final UserEntity entities;
	private final boolean protect;
	private final int focount;
	private final int frcount;
	private final int licount;
	private final Date createdAt;
	private final int favcount;
	private final int utcOffset;
	private final String timeZone;
	private final boolean geoEnabled;
	private final boolean verified;
	private final int stcount;
	private final String lang;
	private final boolean coEnabled;
	private final boolean istr;
	private final boolean trEnabled;
	private final URLPair back;
	private final URLPair icon;
	private final URL banner;
	private final ColorEntity backColor;
	private final ColorEntity linkColor;
	private final ColorEntity sideBorderColor;
	private final ColorEntity sideFillColor;
	private final ColorEntity textColor;
	private final boolean backTile;
	private final boolean useBackImg;
	private final boolean exProfile;
	private final boolean defaultProfile;
	private final boolean defaultImg;
	private final boolean following;
	private final boolean reqSent;
	private final boolean notifications;
	private final String translatorType;

	User(JSONObject o) {
		JSONObject u = o.getJSONObject("user");
		user = new UserIdentity(u);
		location = u.optString("location", null);
		description = u.optString("description", null);
		url = JSONUtil.strToURL(u.optString("url"));
		entities = UserEntity.createEntity(u);
		protect = u.optBoolean("protected");
		focount = u.optInt("followers_count", -1);
		frcount = u.optInt("friends_count", -1);
		licount = u.optInt("listed_count", -1);
		createdAt = JSONUtil.parseDateTime(u.optString("created_at", null));
		favcount = u.optInt("favourites_count", -1);
		utcOffset = u.optInt("utc_offset", 0);
		timeZone = u.optString("time_zone", null);
		geoEnabled = u.optBoolean("geo_enabled");
		verified = u.optBoolean("verified");
		stcount = u.optInt("statuses_count", -1);
		lang = u.optString("lang", null);
		coEnabled = u.optBoolean("contributors_enabled");
		istr = u.optBoolean("is_translator");
		trEnabled = u.optBoolean("is_translation_enabled");
		back = URLPair.createPair(u, "profile_background_image_url");
		icon = URLPair.createPair(u, "profile_image_url");
		banner = JSONUtil.strToURL(u.optString("profile_banner_url"));
		backColor = ColorEntity.createEntity(u, "profile_background_color");
		linkColor = ColorEntity.createEntity(u, "profile_link_color");
		sideBorderColor = ColorEntity.createEntity(u, "profile_sidebar_border_color");
		sideFillColor = ColorEntity.createEntity(u, "profile_sidebar_fill_color");
		textColor = ColorEntity.createEntity(u, "profile_text_color");
		backTile = u.optBoolean("profile_background_tile");
		useBackImg = u.optBoolean("profile_use_background_image");
		exProfile = u.optBoolean("has_extended_profile");
		defaultProfile = u.optBoolean("default_profile");
		defaultImg = u.optBoolean("default_profile_image");
		following = u.optBoolean("following");
		reqSent = u.optBoolean("follow_request_sent");
		notifications = u.optBoolean("notifications");
		translatorType = u.optString("translator_type", null);
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

	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}

	public URL getUrl() {
		return url;
	}

	public UserEntity getEntities() {
		return entities;
	}

	public boolean isProtected() {
		return protect;
	}

	public int getFollowersCount() {
		return focount;
	}

	public int getFriendsCount() {
		return frcount;
	}

	public int getListedCount() {
		return licount;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public int getfavouritesCount() {
		return favcount;
	}

	public int getUTCOffset() {
		return utcOffset;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public boolean isGeoEnabled() {
		return geoEnabled;
	}

	public boolean isVerified() {
		return verified;
	}

	public int getStatusesCount() {
		return stcount;
	}

	public String getLang() {
		return lang;
	}

	public boolean enableContributors() {
		return coEnabled;
	}

	public boolean isTranslator() {
		return istr;
	}

	public boolean enableTranslation() {
		return trEnabled;
	}

	public URL getProfileBackgroundImageURL() {
		return back.getHttp();
	}

	public URL getProfileBackgroundImageSecureURL() {
		return back.getHttps();
	}

	public URL getProfileImageURL() {
		return icon.getHttp();
	}

	public URL getProfileImageSecureURL() {
		return icon.getHttps();
	}

	public URL getProfileBannerURL() {
		return banner;
	}

	public ColorEntity getProfileBackgroundColor() {
		return backColor;
	}

	public ColorEntity getProfileLinkColor() {
		return linkColor;
	}

	public ColorEntity getProfileSidebarBorderColor() {
		return sideBorderColor;
	}

	public ColorEntity getSidebarFillColor() {
		return sideFillColor;
	}

	public ColorEntity getProfileTextColor() {
		return textColor;
	}

	public boolean useProfileBackgroundTile() {
		return backTile;
	}

	public boolean useProfileBackgroundImage() {
		return useBackImg;
	}

	public boolean hasExtendedProfile() {
		return exProfile;
	}

	public boolean useDefaultProfile() {
		return defaultProfile;
	}

	public boolean useDefaultImage() {
		return defaultImg;
	}

	public boolean nowFollowing() {
		return following;
	}

	public boolean alreadyFollowRequestSent() {
		return reqSent;
	}

	public boolean enableNotifications() {
		return notifications;
	}

	public String getTranslatorType() {
		return translatorType;
	}
}

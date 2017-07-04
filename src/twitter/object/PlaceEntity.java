package twitter.object;

import org.json.JSONObject;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author skht777
 */
public class PlaceEntity {
	private final String id;
	private final URL url;
	private final String placeType;
	private final String name;
	private final String fullName;
	private final String countryCode;
	private final String country;
	private final List<PlaceEntity> within;
	private final BoundingBox boundingBox;
	private final Map<String, String> attributes;

	static PlaceEntity createEntity(JSONObject o) {
		return !o.has("place") || o.isNull("place") ? null : new PlaceEntity(o);
	}

	PlaceEntity(JSONObject o) {
		id = o.getString("id");
		url = JSONUtil.strToURL(o.getString("url"));
		placeType = o.getString("place_type");
		name = o.getString("name");
		fullName = o.getString("full_name");
		countryCode = o.getString("country_code");
		country = o.getString("country");
		within = JSONUtil.createAnyEntities(PlaceEntity::createEntity, o, "contained_within");
		boundingBox = new BoundingBox(o);
		JSONObject atr = o.getJSONObject("attributes");
		attributes = Collections.unmodifiableMap(atr.keySet().stream().collect(
				Collectors.toMap(Function.identity(), atr::getString)));
	}

	public String getId() {
		return id;
	}

	public URL getUrl() {
		return url;
	}

	public String getPlaceType() {
		return placeType;
	}

	public String getName() {
		return name;
	}

	public String getFullName() {
		return fullName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getCountry() {
		return country;
	}

	public List<PlaceEntity> getContainedWithin() {
		return within;
	}

	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}
}

package twitter.object;

import org.json.JSONArray;

import java.util.Collections;
import java.util.List;

/**
 * @author skht777
 */
public class Coordinate {
	private final double lat;
	private final double lng;

	static List<Coordinate> createCoordinate(JSONArray a, boolean swap) {
		return a.optJSONArray(0) == null ?
				Collections.singletonList(swap ? new Coordinate(a) : new Coordinate(a).swap()) :
				JSONUtil.createAnyEntities(Coordinate::new, a);
	}

	private Coordinate(JSONArray a) {
		lat = a.getDouble(0);
		lng = a.getDouble(1);
	}

	private Coordinate swap() {
		return new Coordinate(new JSONArray().put(lng).put(lat));
	}

	public double getLatitude() {
		return lat;
	}

	public double getLlongitude() {
		return lng;
	}
}

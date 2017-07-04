package twitter.object;

import org.json.JSONObject;

import java.util.List;

/**
 * @author skht777
 */
public class CoordinateEntitiy {
	private final List<Coordinate> coordinate;
	private final String type;

	static CoordinateEntitiy createEntity(JSONObject o, String name) {
		return !o.has(name) || o.isNull(name) ? null : new CoordinateEntitiy(o.getJSONObject(name), name.equals("geo"));
	}

	private CoordinateEntitiy(JSONObject o, boolean swap) {
		coordinate = Coordinate.createCoordinate(o.getJSONArray("coordinates"), swap);
		type = o.getString("type");
	}

	List<Coordinate> getCoordinateList() {
		return coordinate;
	}

	public Coordinate getCoordinates() {
		return coordinate.get(0);
	}

	public String getType() {
		return type;
	}
}

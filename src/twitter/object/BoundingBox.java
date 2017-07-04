package twitter.object;

import org.json.JSONObject;

import java.util.List;

/**
 * @author skht777
 */
public class BoundingBox {
	private final CoordinateEntitiy coordinate;

	BoundingBox(JSONObject o) {
		coordinate = CoordinateEntitiy.createEntity(o, "bounding_box");
	}

	public List<Coordinate> getCoordinates() {
		return coordinate.getCoordinateList();
	}

	public String getType() {
		return coordinate.getType();
	}
}

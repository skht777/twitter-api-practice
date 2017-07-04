package twitter.object;

import org.json.JSONObject;

/**
 * @author skht777
 */
public class ColorEntity {
	private String color;

	static ColorEntity createEntity(JSONObject o, String name) {
		return o.has(name) ? new ColorEntity(o.getString(name)) : null;
	}

	private ColorEntity(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public int getColorInt() {
		return Integer.parseInt(color, 16);
	}
}

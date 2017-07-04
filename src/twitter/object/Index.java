package twitter.object;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.stream.IntStream;

/**
 * @author skht
 */
public class Index {
	private final int begin;
	private final int end;

	static Index createEntity(JSONObject o) {
		return new Index(o.getJSONArray("indices"));
	}

	private Index(JSONArray a) {
		begin = a.getInt(0);
		end = a.getInt(1);
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}

	public IntStream range() {
		return IntStream.rangeClosed(begin, end);
	}
}

package twitter.object;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author skht777
 */
public class JSONUtil {

	static {
		Locale.setDefault(Locale.US);
	}

	private JSONUtil() {
	}

	public static List<TimeLineEntity> createTimeLineEntity(String str) {
		return createAnyEntities(TimeLineEntity::new, new JSONArray(str));
	}

	public static List<TimeLineEntity> createTimeLineEntity(JSONArray a) {
		return createAnyEntities(TimeLineEntity::new, a);
	}

	static URL strToURL(String str) {
		if (str == null) {
			return null;
		}
		try {
			return new URL(str);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	static Date parseDateTime(String str) {
		if (str == null) {
			return null;
		}
		try {
			return new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy").parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	static <T, R> List<T> createAnyEntities(Function<R, T> f, JSONArray a) {
		return a == null ? Collections.emptyList() : Collections.unmodifiableList(
				IntStream.range(0, a.length()).mapToObj(a::get).map(o -> (R) o)
						.map(f).filter(Objects::nonNull).collect(Collectors.toList()));
	}

	static <T> List<T> createAnyEntities(Function<JSONObject, T> f, JSONObject o, String name) {
		return createAnyEntities(f, o.optJSONArray(name));
	}
}

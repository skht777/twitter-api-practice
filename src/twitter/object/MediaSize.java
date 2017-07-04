package twitter.object;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author skht777
 */
public class MediaSize {
	private int width;
	private int height;
	private ResizeEntity resize;

	public enum ResizeEntity {
		FIT, DIMENSION, SMALL, LARGE;

		private static Map<String, ResizeEntity> m;

		static {
			m = Arrays.stream(ResizeEntity.values())
					.collect(Collectors.toMap(ResizeEntity::toString, Function.identity()));
		}

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

	static Map<MediaEntity.SizeEntity, MediaSize> createSizeMap(JSONObject o) {
		JSONObject size = o.getJSONObject("sizes");
		return Stream.of(MediaEntity.SizeEntity.values())
				.collect(Collectors.toMap(Function.identity(),
						s -> new MediaSize(size.getJSONObject(s.toString()))));
	}

	MediaSize(JSONObject o) {
		width = o.getInt("w");
		height = o.getInt("h");
		resize = ResizeEntity.m.get(o.getString("resize"));
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ResizeEntity getResize() {
		return resize;
	}
}

package twitter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author skht777
 */
public final class TwitterToken {
	private final String CONSUMER_KEY;
	private final String CONSUMER_SECRET;
	private final String ACCESS_TOKEN;
	private final String ACCESS_TOKEN_SECRET;

	public static class TokenFactory {
		private String con, cons, acc, accs;

		public TokenFactory setOAuthConsumerKey(String key) {
			con = key;
			return this;
		}

		public TokenFactory setOAuthConsumerSecret(String key) {
			cons = key;
			return this;
		}

		public TokenFactory setOAuthAccessToken(String key) {
			acc = key;
			return this;
		}

		public TokenFactory setOAuthAccessTokenSecret(String key) {
			accs = key;
			return this;
		}

		public TwitterToken build() {
			return new TwitterToken(con, cons, acc, accs);
		}
	}

	private TwitterToken(final String CONSUMER_KEY,
						 final String CONSUMER_SECRET,
						 final String ACCESS_TOKEN,
						 final String ACCESS_TOKEN_SECRET) {
		this.CONSUMER_KEY = CONSUMER_KEY;
		this.CONSUMER_SECRET = CONSUMER_SECRET;
		this.ACCESS_TOKEN = ACCESS_TOKEN;
		this.ACCESS_TOKEN_SECRET = ACCESS_TOKEN_SECRET;
	}

	Map<String, String> getToken() {
		Map<String, String> m = new TreeMap<>();
		m.put("oauth_consumer_key", CONSUMER_KEY);
		m.put("oauth_token", ACCESS_TOKEN);
		return m;
	}

	String createHashedBase64String(String str) {
		Mac mac = null;
		try {
			String key = String.join("&", CONSUMER_SECRET, ACCESS_TOKEN_SECRET);
			// シークレットを合わせてSHA1の秘密鍵を作る
			SecretKeySpec sk = new SecretKeySpec(key.getBytes(StandardCharsets.US_ASCII), "HmacSHA1");
			mac = Mac.getInstance("HmacSHA1");
			mac.init(sk);
		} catch (NoSuchAlgorithmException | InvalidKeyException e) { // おそらく出ないであろう例外
		}
		// ハッシュ化したシグネチャをBase64に変化する
		return Base64.getEncoder().encodeToString(
				mac.doFinal(str.getBytes(StandardCharsets.US_ASCII)));
	}
}

package twitter;

import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author skht777
 */
public class TwitterUtil {
	private TwitterUtil() {
	}

	static String sendRequest(TwitterToken token, String url, String method, Map<String, String> data) throws IOException {
		Map<String, String> header = createHeader(token);
		Map<String, String> merged = new TreeMap<>(header);
		merged.putAll(data);
		// 繋げる
		String signature = String.join("&",
				method, urlEncode(url), urlEncode(formUrlEncodedContent(merged)));
		header.put("oauth_signature", token.createHashedBase64String(signature));
		// Authorizationの要素が揃ったので文字列にする(エスケープを忘れない)
		String headerString = "OAuth " + header.entrySet().stream()
				.map(e -> String.format("%s=\"%s\"", urlEncode(e.getKey()), urlEncode(e.getValue())))
				.collect(Collectors.joining(", "));
		// クエリストリングはURLではなくパラメータとして渡すのでこのタイミングでURLにくっつける
		if (!method.equals("POST") && !data.isEmpty()) {
			url += "?" + formUrlEncodedContent(data);
		}
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpUriRequest request;
			switch (method) {
				case "GET":
					request = new HttpGet(url);
					break;
				case "DELETE":
					request = new HttpDelete(url);
					break;
				default:
					request = new HttpPost(url);
					// POSTするデータのHttpComponentsの仕様
					List<NameValuePair> postData = data.entrySet().stream()
							.map(e -> new BasicNameValuePair(e.getKey(), e.getValue()))
							.collect(Collectors.toList());
					// 入出力でUTF-8を明示する
					((HttpPost) request).setEntity(new UrlEncodedFormEntity(postData, StandardCharsets.UTF_8));
					break;
			}
			request.setHeader(HttpHeaders.AUTHORIZATION, headerString);
			// レスポンスボディを勝手に文字列にして返してくれるおまじない
			return client.execute(request, res -> EntityUtils.toString(res.getEntity(), "UTF-8"));
		}
	}

	private static Map<String, String> createHeader(TwitterToken token) {
		// TreeMapはcompareToに基づく順序付けMap
		Map<String, String> data = new TreeMap<>(token.getToken());
		data.put("oauth_signature_method", "HMAC-SHA1");
		data.put("oauth_timestamp", String.valueOf(Calendar
				.getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime() / 1000));
		data.put("oauth_nonce", generateNonce());
		data.put("oauth_version", "1.0");
		return data;
	}

	private static String generateNonce() {
		Random rnd = new Random();
		return String.valueOf(123400 + rnd.nextInt(9999999 - 123400));
	}

	private static String formUrlEncodedContent(Map<String, String> data) {
		// Map<K, V>はEntry<K, V>になりkey=value&key=...の形で文字列に変換される
		return data.entrySet().stream()
				.map(e -> urlEncode(e.getKey()) + "=" + urlEncode(e.getValue()))
				.collect(Collectors.joining("&"));
	}

	private static String urlEncode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}
}

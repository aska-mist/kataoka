import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mist36 on 2016/07/05.
 */
public class NicoApi {

    private static final String LOGIN_URL = "https://secure.nicovideo.jp/secure/login?site=niconico";
    private static final String MAIL_ADDRESS = "";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws ClientProtocolException,
            IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(ClientPNames.COOKIE_POLICY,
                org.apache.http.client.params.CookiePolicy.BROWSER_COMPATIBILITY);
        client.getParams().setParameter("http.connection.timeout", 5000);
        client.getParams().setParameter("http.socket.timeout", 3000);

        HttpPost post = new HttpPost(LOGIN_URL);

        // POSTするパラメータ
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

        // mail
        params.add(new BasicNameValuePair("mail", MAIL_ADDRESS));
        // password
        params.add(new BasicNameValuePair("password", PASSWORD));

        post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        HttpResponse response = client.execute(post);
        Header[] headers = response.getAllHeaders();
        for (Header h : headers) {
            System.out.println(h.getName() + ":" + h.getValue());
        }
        // cookie保存
        CookieStore cookieStore = client.getCookieStore();
        client.getConnectionManager().shutdown();

        // getFlvを実行する
        DefaultHttpClient clientFlv = new DefaultHttpClient();
        clientFlv.setCookieStore(cookieStore);
        HttpPost postFlv = new HttpPost(
                "http://flapi.nicovideo.jp/api/getflv/sm29178054");
        HttpResponse responseFlv = clientFlv.execute(postFlv);
        HttpEntity entity = responseFlv.getEntity();
        System.out.println(EntityUtils.toString(entity, "utf-8"));
        clientFlv.getConnectionManager().shutdown();
        System.out.println("end");

    }

}



package pe.ironbit.android.baking.service.base;

import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;

import pe.ironbit.android.baking.data.base.DataParser;

/**
 * Helper class for web request.
 */
public class WebRequestHelper {
    /**
     * Name of the class and it is used for log purpose.
     */
    private static final String LOG_TAG = WebRequestHelper.class.getSimpleName();

    /**
     * Perform the web request and decode of the web response.
     *
     * @param resourceUrl the url for the web request.
     * @param parser      decode of the response to a list of data objects.
     * @return List of data objects.
     */
    public static List makeWebRequest(String resourceUrl, DataParser parser) {
        URL url = null;
        try {
            url = createUrl(resourceUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem creating URL ", e);
            return null;
        }

        String response = null;
        try {
            response = makeHttpRequest(url);
            if (response == null) {
                Log.e(LOG_TAG, "Problem in response.");
                return null;
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem performing web api.", e);
            return null;
        }

        List list = null;
        try {
            list = parser.parse(response);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the results.", e);
            return null;
        }

        return list;
    }

    /**
     * Transform the Uri to Url.
     * @param param Uri in string.
     * @return The Url from the Uri.
     * @throws MalformedURLException
     */
    private static URL createUrl(String param) throws MalformedURLException {
        return new URL(param);
    }

    /**
     * Make the web request.
     * @param url The url with the request.
     * @return The response of the request.
     * @throws IOException
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String response = null;
        HttpURLConnection urlConnection = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setReadTimeout(WebRequestContract.READ_TIMEOUT);
            urlConnection.setConnectTimeout(WebRequestContract.CONNECT_TIMEOUT);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == urlConnection.HTTP_OK) {
                response = readHttpResponse(urlConnection);
            }
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return response;
    }

    /**
     * Transform the binary information to UTF-8 format.
     * @param urlConnection The buffer with the information
     * @return The string in UTF-8 format.
     * @throws IOException
     */
    private static String readHttpResponse(URLConnection urlConnection) throws IOException {
        String response = null;
        InputStream inputStream = null;

        try {
            inputStream = urlConnection.getInputStream();
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            response = output.toString();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return response;
    }
}

//package tests.verticaTest;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class IdCardClient {
//
//    public static final void main(String[] args) throws JSONException, IOException {
//        IdCardClient client = new IdCardClient();
//        JSONObject object = client.readJsonFromUrl();
//        System.out.println( "Response >> " + client.toString());
//    }
//
//
//    /**
//     * @return
//     * @throws IOException
//     * @throws JSONException
//     */
//    private JSONObject readJsonFromUrl() throws IOException, JSONException {
//        System.setProperty("javax.net.ssl.keyStore", "C:\\mystuff\\keystore.jks");
//        System.setProperty("https.protocols", "TLSv1,SSLv3");
//        System.setProperty("javax.net.ssl.keyStorePassword", "XXXXX");
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        String jsonText = getJson(httpClient);
//        System.out.println("JSON Text : " + jsonText);
//        JSONObject json = new JSONObject(jsonText);
//        return json;
//    }
//
//
//    /**
//     * @param httpClient
//     * @return
//     * @throws IOException
//     */
//    private String getJson(CloseableHttpClient httpClient) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        try {
//            HttpGet getRequest = new HttpGet("https://cloudservicegateway-stg.optum.com:4460/test1/healthapi/benefits/idcard/v2.0/index.json");
//            getRequest.addHeader("accept", "application/json");
//            getRequest.addHeader("userid", "swordsub");
//            HttpResponse response = httpClient.execute(getRequest);
//            System.out.println("HTTP Response status code : " + response.getStatusLine().getStatusCode());
//            String output;
//            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
//            while ((output = br.readLine()) != null) {
//                sb.append(output);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        httpClient.close();
//        return sb.toString();
//    }
//
//
//
//}

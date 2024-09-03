package Connection;

import com.alibaba.fastjson.JSONObject;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

/**
 * @author hx
 * @version 1.0
 * @date 2024/9/3 10:02
 */
public class connhttps {
//    public static void main(String[] args) {
//        try {
//            // 创建URL对象
//            URL url = new URL("https://");
//
//            // 创建HttpURLConnection对象
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            // 设置请求方法为POST
//            connection.setRequestMethod("POST");
//
//            // 设置请求头
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//
//            // 启用输出流
//            connection.setDoOutput(true);
//
//            // 创建请求体
//            String requestBody = p.toJSONString();
//
//            // 将请求体写入输出流
//            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
//            outputStream.write(requestBody.getBytes(StandardCharsets.UTF_8));
//            outputStream.flush();
//            outputStream.close();
//
//            // 发送请求并获取响应码
//            int responseCode = connection.getResponseCode();
////            System.out.println(connection.getContent());
//
//            // 检查响应码
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                // 读取响应内容
//                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                StringBuilder response = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    response.append(line);
//                }
//                reader.close();
//
//                // 处理响应内容
//                System.out.println(response.toString());
//            } else {
//                // 处理错误响应
//                System.out.println("请求失败,响应码: " + responseCode);
//            }
//
//            // 关闭连接
//            connection.disconnect();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /*public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    /*public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization","Bearer ")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization","Bearer ")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
//        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("https://")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("client_id", "")
                .field("client_secret", "")
                .field("scope", "")
                .field("grant_type", "")
                .asString();
        System.out.println(response.getBody());
    }*/

    /*public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        InputStream is = null;
        OutputStream os = null;
        URL url = new URL("https://");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
//            SSLContext sslContext=SSLContext.getInstance("SSL");
//            TrustManager[] tm={new MyX509TrustManager()};
//            MyX509TrustManager.trustAllHttpsCertificates();
//            HttpsURLConnection.setDefaultHostnameVerifier(hv);
//            sslContext.init(null, tm, new java.security.SecureRandom());

            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
// 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
// 设置请求头部信息
            connection.setRequestProperty("xxx", "xxx");
            connection.setRequestProperty("xxx", "xxx");
            os = connection.getOutputStream();
            String client_id = "";
            String client_secret="";
            String scope="";
            String grant_type="";
            JSONObject p = new JSONObject();
            p.put("client_id",client_id);
            p.put("client_secret",client_secret);
            p.put("scope",scope);
            p.put("grant_type",grant_type);
            os.write(p.toString().getBytes());
            System.out.println(connection.getResponseCode());
            is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sbf = new StringBuffer();
            String temp = null;
// 循环遍历一行一行读取数据
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }
            String result = sbf.toString();
            System.out.println(result);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
// 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
// 断开与远程地址url的连接
            connection.disconnect();
        }
//        return result;

    }*/

    /*public static void main(String[] args) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) new URL("url").openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod("POST");
//            conn.setConnectTimeout(4 * 1000);
            conn.setDefaultUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
//            conn.setUseCaches(false);
//            String client_id = "";
//            String client_secret="";
//            String scope="";
//            String grant_type="";
//            JSONObject p = new JSONObject();
//            p.put("grant_type",grant_type);
//            p.put("scope",scope);
//            p.put("client_secret",client_secret);
//            p.put("client_id",client_id);
//            System.out.println(p.toString());
            String p = "";
            // 创建请求体
//            String requestBody = p.toString();
//            OutputStream outputStream = conn.getOutputStream();
//            outputStream.write(requestBody.getBytes());
//            conn.getOutputStream().write(requestBody.getBytes());
//            outputStream.flush();
//            outputStream.close();
//            conn.connect();
            // 获取URLConnection对象对应的输出流
            OutputStreamWriter ouw = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
//            ouw.write(requestBody);
            out = new PrintWriter(ouw);
            out.print(p.getBytes());
            // flush输出流的缓冲
//            out.flush();
            InputStream is = conn.getInputStream();
//            if (conn.getResponseCode() >= 400) {
//
//                is = conn.getErrorStream();
//            } else {
//                is = conn.getInputStream();
//            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(result);
    }*/
}

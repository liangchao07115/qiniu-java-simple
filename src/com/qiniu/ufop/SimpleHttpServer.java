package com.qiniu;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        SpeechUtility.createUtility(SpeechConstant.APPID + "=????");

        HttpServer server = HttpServer.create(new InetSocketAddress(9100), 0);
        server.createContext("/uop", new testHander());
        server.setExecutor(null);
        server.start();
    }

    static class testHander implements HttpHandler {

        private ObjectMapper objMapper;

        public testHander() {
            objMapper = new ObjectMapper();
            objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        @Override
        public void handle(HttpExchange he) throws IOException {
            InputStream is = he.getRequestBody();
            StringBuffer mResult = new StringBuffer();
            testRecogListener recListener = new testRecogListener(mResult);

            UfopReq req;

            try {
                req = objMapper.readValue(is, UfopReq.class);
            } catch (JsonMappingException e) {
                System.err.println("[UFOP] invalid request body");
                e.printStackTrace();
                he.sendResponseHeaders(400, -1);
                return;
            }

            SpeechRecognizer recognizer = SpeechRecognizer.createRecognizer();
            recognizer.setParameter(SpeechConstant.DOMAIN, "iat");
            recognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            recognizer.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
            recognizer.setParameter(SpeechConstant.RESULT_TYPE, "plain");
            System.out.println("准备进入listening"+ req.getSrc().getUrl());
            recognizer.startListening(recListener);

            try {
                URL srcUrl = new URL(req.getSrc().getUrl());
                URLConnection conn = srcUrl.openConnection();
                conn.connect();
                InputStream fileIs = conn.getInputStream();
                byte[] buffer = new byte[9600];
                int len = -1;
                while ((len = fileIs.read(buffer)) != -1) {

                    recognizer.writeAudio(buffer, 0, len);
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                System.err.println("[UFOP] GET failed");
                he.sendResponseHeaders(400, 0);
            }

            recognizer.stopListening();

            while (true) {
                if (recognizer.isListening() == false) {
                    System.out.println("[UFOP] 会话结束");
                    break;
                }
            }

            byte[] resultB = mResult.toString().getBytes(Charset.forName("UTF-8"));
            he.sendResponseHeaders(200, resultB.length);
            OutputStream os = he.getResponseBody();
            os.write(resultB);
            os.close();
            System.out.println("[UFOP] 处理完成");
        }

    }
}



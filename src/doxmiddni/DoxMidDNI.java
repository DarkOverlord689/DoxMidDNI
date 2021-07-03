/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doxmiddni;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;

/**
 *
 * @author DarkOvs77
 */
public class DoxMidDNI {

    /**
     * @param args the command line arguments
     */
    public static String Consulta(String doc) throws IOException, JSONException {
        // TODO code application logic here
        URL url_api = new URL("http://18.117.111.252:4001/v1/consultas/reniec/intermedio/dni");
        //POST
        HttpURLConnection conServer = (HttpURLConnection) url_api.openConnection();
        conServer.setRequestMethod("POST");
        conServer.setRequestProperty("Content-Type", "application/json");
        conServer.setRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZCI6IlZtMTBVMUV4VlhsVGJrNVNZa1Z3VWxaclVrSlBVVDA5Iiwic2Vzc2lvbiI6ImRlMzhlYWZlLTUzMWQtNGU4Mi1hOTVjLTM5NmUzNzgzZWM1Mi5iMDYyZmU4NS1hMmI4LTQyZTctYTBkNi03ZTgyNTliY2UzNGEiLCJleHAiOjE2MjMxMDAzMjF9.N3W-v_JSP_HWdQVoChu8SwowM2n3RjcVgBgc7OIYBWV4R_DDMHEF1BoE0uiw9TPnvTUTOOI_3t0PPtEd0lIXJQ");
        conServer.setDoOutput(true);

        String postData = "{\"dni\":\"" + doc + "\"}"; //{"dni":"75385200"}

        try (OutputStream SFlujo = (OutputStream) conServer.getOutputStream()) {
            byte[] salida = postData.getBytes("utf-8");
            SFlujo.write(salida, 0, salida.length);
        }

        try (BufferedReader BuffRead = new BufferedReader(
                new InputStreamReader(conServer.getInputStream(), "utf-8"))) {
            StringBuilder Sb = new StringBuilder();
            String responseObj = null;

            while ((responseObj = BuffRead.readLine()) != null) {
                Sb.append(responseObj.trim());
            }
            return Sb.toString();
            //JSONObject objData = new JSONObject(Sb.toString());
        }
    }

}

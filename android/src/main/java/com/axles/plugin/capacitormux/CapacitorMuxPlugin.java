package com.axles.plugin.capacitormux;

import android.app.Activity;
import android.content.Context;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@CapacitorPlugin(name = "CapacitorMux")
public class CapacitorMuxPlugin extends Plugin {

    private CapacitorMux implementation = new CapacitorMux();

    @PluginMethod
    public void echo(PluginCall call) {

        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void uploadVideo(PluginCall call) {

        String urlToConnect = call.getString("uploadUri");
        String fileToUpload = call.getString("videoFile");
        String lineEnd = "\r\n";
        int chunkSize = 256 * 1024; // chunk size of 256KB

        try {
            FileInputStream fileInputStream = new FileInputStream(fileToUpload);
            long totalSize = fileInputStream.getChannel().size();
            long totalChunks = (totalSize + chunkSize - 1) / chunkSize;
            long uploadedBytes = 0;

            for (long chunk = 0; chunk < totalChunks; chunk++) {
                URL url = new URL(urlToConnect);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Allow Inputs & Outputs
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);

                // Enable PUT method
                connection.setRequestMethod("PUT");

                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("Content-Type", "multipart/form-data");

                byte[] buffer = new byte[chunkSize];
                int bytesRead = fileInputStream.read(buffer, 0, chunkSize);

                connection.setRequestProperty("Content-Length", String.valueOf(bytesRead));
                connection.setRequestProperty("Content-Range", "bytes " + uploadedBytes + "-" + (uploadedBytes + bytesRead - 1) + "/" + totalSize);

                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

                outputStream.write(buffer, 0, bytesRead);

                outputStream.flush();
                outputStream.close();

                // Responses from the server (code and message)
                int serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();

                if (serverResponseCode == 308) {
                    // Continue uploading next chunk
                    uploadedBytes += bytesRead;
                } else if (serverResponseCode == 200 || serverResponseCode == 201) {
                    // Upload completed
                    break;
                } else {
                    // Handle error
                }
            }

            fileInputStream.close();

        } catch (Exception ex) {
            // Exception handling
            System.out.println("errorrrrrrrrrrrrrrrrr!" + ex);
        }

        System.out.println("should of worked!");

    }

}

package net.kunmc.lab.retranslation;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.kunmc.lab.retranslation.config.ConfigManager;
import org.bukkit.craftbukkit.libs.org.apache.http.HttpStatus;
import org.bukkit.craftbukkit.libs.org.apache.http.client.methods.CloseableHttpResponse;
import org.bukkit.craftbukkit.libs.org.apache.http.client.methods.HttpGet;
import org.bukkit.craftbukkit.libs.org.apache.http.impl.client.HttpClients;
import org.bukkit.craftbukkit.libs.org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Retranslation {
    private static final String TRANSLATE_API_URL = "https://script.google.com/macros/s/{url}/exec?text={text}&relays={relays}";

    public static String retranslate(String text) throws IOException {
        ConfigManager configManager = RetranslationPlugin.getInstance().getConfigManager();
        String relays = String.join("/", configManager.getRelays()) + "/ja";
        String url = TRANSLATE_API_URL
                .replace("{url}", configManager.getDeployId())
                .replace("{text}", URLEncoder.encode(text, StandardCharsets.UTF_8.name()))
                .replace("{relays}", relays);
        CloseableHttpResponse response = HttpClients.createDefault().execute(new HttpGet(url));
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new IOException(response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
        }
        JsonElement element = new JsonParser().parse(EntityUtils.toString(response.getEntity()));
        if (element.getAsJsonObject().getAsJsonPrimitive("code").getAsString().equals("error")) {
            throw new IOException(element.getAsJsonObject().get("message").getAsString());
        }
        return element.getAsJsonObject().get("retranslated").getAsString();
    }
}

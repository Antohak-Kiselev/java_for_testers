package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.List;


public class TestBase {


  private boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    List<Issue> js = new Gson().fromJson(issues, new TypeToken<List<Issue>>() {
    }.getType());
    return !js.get(0).getState_name().equals("Resolved");
  }


  private Executor getExecutor() {
    return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      System.out.println("Ignored because of issue " + issueId);
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}


package com.disenio.mimagrupo06.presentacion;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

  private final Handlebars handlebars = new Handlebars();

  public IndexController() {
  }

  @GetMapping("/")
  public ResponseEntity<String> index() throws IOException {

    Template template = handlebars.compile("/Template/login");

    Map<String, Object> model = new HashMap<>();

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }
}

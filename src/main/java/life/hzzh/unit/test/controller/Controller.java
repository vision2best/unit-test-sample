package life.hzzh.unit.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

    @PostMapping("student/save")
    public ResponseEntity<String> studentSave(String student) {
        return ResponseEntity.ok(student);
    }
}

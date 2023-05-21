package xyz.ruankun.xiangchengliangbanji.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ruankun.xiangchengliangbanji.service.SpeechRestfulService;

@RestController
@RequestMapping("/speech")
public class TtsController {

    @GetMapping("/text2voice")
    public ResponseEntity<Resource> text2voice(@RequestParam("text") String text) {
        byte[] audioBytes = SpeechRestfulService.text2voice(text);
        if (audioBytes != null) {
            ByteArrayResource resource = new ByteArrayResource(audioBytes);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=speech.wav");
            headers.add(HttpHeaders.CONTENT_TYPE, "audio/wav");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(audioBytes.length)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

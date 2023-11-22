package io.nurgissa.queueoverflow.controller;


import io.nurgissa.queueoverflow.dto.TagDto;
import io.nurgissa.queueoverflow.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

//    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PostMapping
    public ResponseEntity<?> createTag(@RequestBody TagDto tagDto){
        System.out.println("ffsfdsf");
        tagService.createTag(tagDto);
        return ResponseEntity.accepted().body("Tag successfully created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByTagId(@PathVariable Long id){
        return ResponseEntity.ok(tagService.getByTagId(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTagById(@PathVariable Long id){
        tagService.deleteTagById(id);
        return ResponseEntity.accepted().body("Successfully deleted");
    }
}

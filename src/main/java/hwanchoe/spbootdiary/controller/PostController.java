package hwanchoe.spbootdiary.controller;

import hwanchoe.spbootdiary.dto.PageRequestDTO;
import hwanchoe.spbootdiary.dto.PageResponseDTO;
import hwanchoe.spbootdiary.dto.PostDTO;
import hwanchoe.spbootdiary.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Tag(name = "Post API", description = "API for managing posts")
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public ResponseEntity<PageResponseDTO<PostDTO>> list(PageRequestDTO pageRequestDTO) {
        PageResponseDTO<PostDTO> responseDTO = postService.list(pageRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/register")
    public ResponseEntity<Void> registerGET() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/calendar")
    public ResponseEntity<Void> calendarGET() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerPost(@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors occurred");
        }
        int id = postService.register(postDTO);
        return ResponseEntity.ok("Post registered with ID: " + id);
    }

    @GetMapping({"/read", "/modify"})
    public ResponseEntity<PostDTO> read(@RequestParam int id) {
        PostDTO postDTO = postService.readOne(id);
        return ResponseEntity.ok(postDTO);
    }

    @PostMapping("/modify")
    public ResponseEntity<String> modify(@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors occurred");
        }
        postService.modify(postDTO);
        return ResponseEntity.ok("Post modified");
    }

    @PostMapping("/remove")
    public ResponseEntity<String> remove(@RequestParam int id) {
        postService.remove(id);
        return ResponseEntity.ok("Post removed");
    }
}

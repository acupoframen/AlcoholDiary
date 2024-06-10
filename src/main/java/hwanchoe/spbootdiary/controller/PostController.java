package hwanchoe.spbootdiary.controller;

import hwanchoe.spbootdiary.domain.Post;
import hwanchoe.spbootdiary.dto.PageRequestDTO;
import hwanchoe.spbootdiary.dto.PageResponseDTO;
import hwanchoe.spbootdiary.dto.PostDTO;
import hwanchoe.spbootdiary.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<PostDTO> responseDTO= postService.list(pageRequestDTO);
        model.addAttribute("responseDTO",responseDTO);
        model.addAttribute("events",responseDTO.getDtoList());
    }
    @GetMapping("/register")
    public void registerGET(){
        ;
    }

    @GetMapping("/calendar")
    public void calendarGET(){
        ;
    }

    @PostMapping("/register")
    public String registerPost(@Valid PostDTO postDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/post/register";
        }
        int id = postService.register(postDTO);
        redirectAttributes.addFlashAttribute("result",id);
        return "redirect:/post/list";
    }
    @GetMapping({"/read","/modify"})
    public void read(int id, PageRequestDTO pageRequestDTO, Model model){
        PostDTO postDTO = postService.readOne(id);
        model.addAttribute("dto",postDTO);

    }
    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO, @Valid PostDTO postDTO,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("id", postDTO.getId());
            return "redirect:/post/modify?" + link;
        }
        postService.modify(postDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("id", postDTO.getId());
        return "redirect:/post/read";
    }
    @PostMapping("/remove")
    public String remove (int id, RedirectAttributes redirectAttributes){
        postService.remove(id);
        redirectAttributes.addFlashAttribute("result","removed");
        return "redirect:/post/list";
    }

}
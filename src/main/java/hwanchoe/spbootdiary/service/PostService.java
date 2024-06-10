package hwanchoe.spbootdiary.service;

import hwanchoe.spbootdiary.dto.PageRequestDTO;
import hwanchoe.spbootdiary.dto.PageResponseDTO;
import hwanchoe.spbootdiary.dto.PostDTO;

public interface PostService {
    int register (PostDTO postDTO);
    PostDTO readOne(int id);

    void modify(PostDTO postDTO);
    void remove (int id);
    PageResponseDTO <PostDTO> list (PageRequestDTO pageRequestDTO);
}

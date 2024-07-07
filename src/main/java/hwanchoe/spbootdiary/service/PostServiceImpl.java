package hwanchoe.spbootdiary.service;

import hwanchoe.spbootdiary.domain.Post;
import hwanchoe.spbootdiary.dto.PageRequestDTO;
import hwanchoe.spbootdiary.dto.PageResponseDTO;
import hwanchoe.spbootdiary.dto.PostDTO;
import hwanchoe.spbootdiary.dto.PostListReplyCountDTO;
import hwanchoe.spbootdiary.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    @Override
    public int register(PostDTO postDTO){
        Post post=modelMapper.map(postDTO,Post.class);
        int id=postRepository.save(post).getId();
        return id;
    }

    @Override
    public PostDTO readOne(int id){
        Optional <Post> result= postRepository.findById(id);
        Post post= result.orElseThrow();
        PostDTO postDTO=modelMapper.map(post, PostDTO.class);
        return postDTO;
    }

    @Transactional
    @Override
    public void modify(PostDTO postDTO){
        Optional<Post> result= postRepository.findById(postDTO.getId());
        Post post=result.orElseThrow();
        post.change(postDTO.getTitle(),postDTO.getDetail(), postDTO.getTypeofalcohol(),postDTO.getLocation() );
        postRepository.save(post);
    }

    @Override
    public void remove(int id){
        postRepository.deleteById(id);
    }

    @Override
    public PageResponseDTO<PostDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types=pageRequestDTO.getTypes();
        String keyword=pageRequestDTO.getKeyword();
        Pageable pageable=pageRequestDTO.getPageable("id");
        Page<Post> result = postRepository.searchAll(types,keyword,pageable);
        List<PostDTO>  dtoList=result.getContent().stream()
                .map(post->modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
                return PageResponseDTO.<PostDTO>withAll()
                        .pageRequestDTO(pageRequestDTO)
                        .dtoList(dtoList)
                        .total((int)result.getTotalElements())
                        .build();
    }

    @Override
    public PageResponseDTO<PostListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("id");
        Page<PostListReplyCountDTO> result= postRepository.searchWithReplyCount(types,keyword,pageable);
        return PageResponseDTO.<PostListReplyCountDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.getContent())
                .total((int)result.getTotalElements())
                .build();
    }


}


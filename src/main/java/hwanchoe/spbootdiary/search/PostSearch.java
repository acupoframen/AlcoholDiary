package hwanchoe.spbootdiary.search;

import hwanchoe.spbootdiary.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PostSearch {
    Page<Post> search1(Pageable pageable);

    Page<Post> searchAll(String[] types, String keyword, Pageable pageable);
}

package hwanchoe.spbootdiary.repository;

import hwanchoe.spbootdiary.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    @Query("select r from Reply r where r.post.id=:id")
    Page <Reply> listofBoard (int id, Pageable pageable);
}

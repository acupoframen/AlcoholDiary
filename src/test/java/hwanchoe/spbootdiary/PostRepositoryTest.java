package hwanchoe.spbootdiary;

import hwanchoe.spbootdiary.domain.Post;
import hwanchoe.spbootdiary.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Test
    public void testInsert(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
        IntStream.rangeClosed(1,100).forEach(i->{
            Post post= Post.builder()
                        .title("Test: DAY "+i)
                        .detail("hello~"+i)
                    .user("hwan_mod")
                        .build();
           Post result=  postRepository.save(post);
        });
    }
}

package hwanchoe.spbootdiary.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Reply", indexes={@Index(name="idx_reply_post_id", columnList = "post_id")})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="post")
public class Reply extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
    private String replyText;
    private String replyer;
}

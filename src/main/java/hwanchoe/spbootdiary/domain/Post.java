package hwanchoe.spbootdiary.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String detail;


    @Column(length = 500, nullable = true)
    private String typeofalcohol;

    @Column(length = 500, nullable = true)
    private String location;

    @Column(length = 500, nullable = false)
    private String user;

    public void change(String title, String detail, String typeofalcohol, String location) {
        this.title = title;
        this.detail = detail;
        this.typeofalcohol = typeofalcohol;
        this.location = location;
        this.user = user;
    }
}

package hwanchoe.spbootdiary.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private int id;
    @NotEmpty
    @Size(min=1, max=100)
    private String title;
    @NotEmpty
    private String detail;
    private String typeofalcohol;
    private String location;
    @NotEmpty
    private String user;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

}

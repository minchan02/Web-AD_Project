package org.example.sprinweb.board;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Board {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @Column(columnDefinition = "text")
    private String content;

    private String storedName;

    private String originalName;

    private String writerName;
}

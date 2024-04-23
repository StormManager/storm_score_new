package com.storm.score.model;

import com.storm.score.model.base_entity.TimeStampedOnlyCreatedAt;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.storm.score.model
 * fileName       : ScoreImage
 * author         : ojy
 * date           : 2024/04/21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/21        ojy       최초 생성
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name = "SCORE_IMAGE")
public class ScoreImage extends TimeStampedOnlyCreatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCORE_IMAGE_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "SCORE_ID", nullable = false)
    private Score score;

    @Column(name = "URL", nullable = false, length = 500)
    private String url;

    @Column(name = "`INDEX`", nullable = false)
    private Integer index;

    @Builder
    public ScoreImage(Score score, String url, Integer index) {
        this.score = score;
        score.addScoreImage(this);

        this.url = url;
        this.index = index;
    }
}


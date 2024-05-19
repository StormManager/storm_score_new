package com.storm.score.model;

import com.storm.score.model.base_entity.TimeStamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.storm.score.model
 * fileName       : Score
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
@Table(name = "SCORE")
public class Score extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCORE_ID", nullable = false)
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 45)
    private String title;

    @Column(name = "INSTRUMENT", nullable = false, length = 45)
    private String instrument;

    @Column(name = "SINGER", nullable = false, length = 45)
    private String singer;

    @OneToMany(mappedBy = "score", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ScoreImage> scoreImageList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Builder
    public Score(String title, String instrument, String singer, User user) {
        this.title = title;
        this.instrument = instrument;
        this.singer = singer;
        user.addScore(this);
    }

    public void addScoreImage(ScoreImage scoreImage) {
        this.scoreImageList.add(scoreImage);
    }

    public void regUser(User user) {
        this.user = user;
    }
}
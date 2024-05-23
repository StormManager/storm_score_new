package com.storm.score.model;

import com.storm.score.model.base_entity.TimeStampedOnlyCreatedAt;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "MESSAGE", schema = "STORM_SCORE")
public class Message extends TimeStampedOnlyCreatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SCORE_ID")
    private Score score;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Builder
    public Message(Room room, User user, Score score, String text) {
        room.addMessage(this);
        user.addMessage(this);
        score.addMessage(this);
        this.text = text;
    }

    public void regRoom(Room room) {
        this.room = room;
    }

    public void regUser(User user) {
        this.user = user;
    }

    public void regScore(Score score) {
        this.score = score;
    }
}

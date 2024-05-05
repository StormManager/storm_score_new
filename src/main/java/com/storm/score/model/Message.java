package com.storm.score.model;

import com.storm.score.em.MessageType;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "MESSAGE_TYPE", nullable = false)
    private MessageType messageType;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Builder
    public Message(Room room, User user, MessageType messageType, String content) {
        this.room = room;   // TODO : 양방향 고려
        this.user = user;   // TODO : 양방향 고려
        this.messageType = messageType;
        this.content = content;
    }
}

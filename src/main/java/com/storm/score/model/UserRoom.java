package com.storm.score.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "USER_ROOM", schema = "STORM_SCORE")
public class UserRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ROOM_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Builder
    public UserRoom(Room room, User user) {
        room.addUserRoom(this);
        user.addUserRoom(this);
    }

    public void regRoom(Room room) {
        this.room = room;
    }

    public void regUser(User user) {
        this.user = user;
    }
}
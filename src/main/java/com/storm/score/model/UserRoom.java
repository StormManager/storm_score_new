package com.storm.score.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USERROOM", schema = "STORM_SCORE")
public class UserRoom {
    @EmbeddedId
    private UserRoomId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @MapsId("roomId")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;

}
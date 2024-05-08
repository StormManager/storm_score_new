package com.storm.score.model;

import com.storm.score.model.base_entity.TimeStamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.storm.score.model
 * fileName       : Room
 * author         : ojy
 * date           : 2024/04/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/19        ojy       최초 생성
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name = "ROOM")
public class Room extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID", nullable = false)
    private Long id;

    @Column(name = "CREATED_USER_ID", nullable = false)
    private Long createdUserId;

    @Column(name = "TITLE", nullable = false, length = 45)
    private String title;

    @Column(name = "PASSWORD", nullable = false, length = 45)
    private String password;

    @Column(name = "MAX_CAPACITY", nullable = false)
    private Integer maxCapacity;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @BatchSize(size = 100)
    private List<UserRoom> userRoomList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @BatchSize(size = 100)
    private List<Message> messageList = new ArrayList<>();

    @Builder
    public Room(Long createdUserId, String title, String password, Integer maxCapacity) {
        this.createdUserId = createdUserId;
        this.title = title;
        this.password = password;
        this.maxCapacity = maxCapacity;
    }

    public void addUserRoom(UserRoom userRoom) {
        this.userRoomList.add(userRoom);
    }

    public void addMessage(Message message) {
        this.messageList.add(message);
    }
}
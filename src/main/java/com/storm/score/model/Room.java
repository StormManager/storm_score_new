package com.storm.score.model;

import com.storm.score.model.base_entity.TimeStamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@Builder
@AllArgsConstructor
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
}
package com.storm.score.model;

import com.storm.score.em.UserRole;
import com.storm.score.model.base_entity.TimeStamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * description    :
 * packageName    : com.storm.score.model
 * fileName       : User
 * author         : wammelier
 * date           : 2024/04/17
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/17        wammelier       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "USER")
public class User extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID")
  private Long id;

  @Column(nullable = false, name="NICKNAME")
  private String nickName;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "USER_PWD")
  private String userPwd;

  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(value = EnumType.STRING)
  @JoinTable(name = "USER_ROLE",
          joinColumns = @JoinColumn(name = "USER_ID"))
  @Column(name = "ROLE")  // USER_ROLE 테이블의 ROLE 컬럼으로 정상 할당 됨 (에러 무시)
  private Set<UserRole> userRoleSet = new HashSet<>(Set.of(UserRole.USER));

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<UserRoom> userRoomList = new ArrayList<>();

  @Builder
  public User(String nickName, String email, String userPwd) {
    this.nickName = nickName;
    this.email = email;
    this.userPwd = userPwd;
  }

  public void updateUserPwd(String userPwd) {
    this.userPwd = userPwd;
  }

  public void addRoleList(Collection<String> userRoleList) {
    for (String role : userRoleList) {
      this.userRoleSet.add(UserRole.valueOf(role.toUpperCase()));
    }
  }

  public void addUserRoom(UserRoom userRoom) {
    userRoomList.add(userRoom);
  }
}

package com.spring.toyproject.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * 다대다 관계를 해소하는 중간 행위테이블
 * fk를 2개 갖고 있음
 * 어떤 여행일지에 어떤 태그가 달렸는지를 로그파일 처럼 누적저장
 *
 * 식별 관계를 할 것이냐 비식별관계로 구현할 것이냐?
 *
 * 식별관계(강한 의존관계)
 * create table travel_log_tag(
 *  travel_log_id (fk, pk)
 *  tag_id (fk, pk)
 *  constraint pk_tv_log_tag primary key(travel_log_id, tag_id)
 * );
 *
 * jpa 는 id가 무조건 하나여야함
 * 두개 이상 사용하고자 하는 경우에는 복합키로 설정하는 클래스를 새롭게 생성해야 함
 *
 * 실제로 식별관계여도 jpa 를 사용하는 경우에는 비식별 관계로 작성해야 함
 * 비식별관계(약한 의존관계)
 * create table travel_log_tag(
 *  id (pk)
 *  travel_log_id (fk)
 *  tag_id (fk)
 * );
 */
@Entity
@Table(name = "travel_log_tags")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelLogTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_log_id", nullable = false)
    private TravelLog travelLog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public TravelLogTag(TravelLog travelLog, Tag tag) {
        this.travelLog = travelLog;
        this.tag = tag;
    }
}

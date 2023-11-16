package shop.ssap.ssap.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "auctions")
public class Auction {
    //ToDo: 테이블 필드 검증 추가 필요

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String startTime;

    @Column
    private String endTime;

//    private String taskId;

//    private String winningBidId;
}

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
@Table(name = "bids")
public class Bid {
    //ToDo: 테이블 필드 검증 추가 필요

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String amount;

    @Column
    private String time;

    @Column
    private String termsAgreed;

    @Column
    private String bidLocationRoadAddress;

    @Column
    private String bidLocationJibunAddress;

//    private Long userId;
//
//    private String auctionId;
}

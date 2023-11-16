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
@Table(name = "point_payments")
public class PointPayment {
    //ToDo: 테이블 필드 검증 추가 필요

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String amount;

    private String point;

    private String paymentMethod;

    private String paymentStatus;

    private String time;

    private String termsAgreed;

//    private Long userId;
}

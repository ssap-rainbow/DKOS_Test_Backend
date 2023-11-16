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
@Table(name = "task_payments")
public class TaskPayment {
    //ToDo: 테이블 필드 검증 추가 필요

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String amount;

    private String paymentStatus;

    private String time;

//    private String userId;
//
//    private String helperId;
//
//    private String taskId;
}

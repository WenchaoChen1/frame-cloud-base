package com.gstdev.cloud.service.system.domain.entity;//package com.gstdev.cloud.service.system.pojo.entity;
//
//import com.gstdev.cloud.data.core.entity.BasePOJOEntity;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//
//import java.util.Objects;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "email")
//@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
//public class SysEmail extends BasePOJOEntity {
//
//    @Column(name = "type")
//    private int type;
//
//    @Column(name = "sender_email", nullable = false, length = 200)
//    private String senderEmail;
//
//    @Column(name = "receiver_email", nullable = false, length = 200)
//    private String receiverEmail;
//
//    @Column(name = "subject", nullable = false, length = 255)
//    private String subject;
//
//    @Column(name = "body", nullable = false, length = 1000)
//    private String body;
//
//    @Column(name = "token")
//    private String token;
//
//    @Column(name = "status", nullable = false)
//    private int Status;
//
//    @Column(name = "is_deleted")
//    private Boolean deleted = false;
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId());
//    }
//}

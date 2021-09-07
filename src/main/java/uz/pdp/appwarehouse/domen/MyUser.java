package uz.pdp.appwarehouse.domen;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "my_user")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String  firstName;

    @Column(name = "last_name")
    private String  lastName;

    @Column(name = "phone_number")
    private String  phoneNumber;

    @Column(name = "code")
    private Integer  code;

    @Column(name = "password")
    private String  password;

    @Column(name = "active")
    private boolean  active;
}

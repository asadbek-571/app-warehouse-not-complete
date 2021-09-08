package uz.pdp.appwarehouse.domen;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String  firstName;

    @Column(name = "last_name")
    private String  lastName;

    @Column(name = "phone_number",unique = true)
    private String  phoneNumber;

    @Column(name = "code")
    private Long  code;

    @Column(name = "password")
    private String  password;

    @Column(name = "active")
    private boolean  active;

    @ManyToMany
    private Set<Warehouse> warehouses;
}

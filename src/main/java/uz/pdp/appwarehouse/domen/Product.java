package uz.pdp.appwarehouse.domen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appwarehouse.domen.abs.AbsEntity;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends AbsEntity {

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "code")
    private Integer code;

    @ManyToOne
    @JoinColumn(name = "measurement_id")
    private Measurement measurement;

}

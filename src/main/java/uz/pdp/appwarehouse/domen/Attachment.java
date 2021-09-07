package uz.pdp.appwarehouse.domen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_name")
    private String  originalName;

    @Column(name = "size")
    private Long  size;

    @Column(name = "name")
    private String  name;

    @Column(name = "content_type")
    private String  contentType;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}

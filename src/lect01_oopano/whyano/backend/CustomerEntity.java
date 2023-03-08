package lect01_2_oopano.whyano.backend;

/**
 * @overview 
 *
 * @author Duc Minh Le (ducmle)
 *
 * @version 
 */
@Entity
@Table(name="Customer")
public class CustomerEntity {
  @Id
  @Column
  private int id;
  
  @Column(length=50)
  private String name;
}

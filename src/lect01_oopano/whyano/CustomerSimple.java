package lect01_oopano.whyano;

import utils.DomainConstraint;
import utils.NotPossibleException;

/**
 * Represents customers of a business organisation.
 * 
 * @author Duc Minh Le (ducmle)
 *
 * @version 
 */
public class CustomerSimple {
  @DomainConstraint(min=1)
  private int id;
  
  private String name;

  public CustomerSimple(int id, String name) {
    if (validateId(id)) {
      this.id = id;
    } else {
      throw new NotPossibleException("CustomerSimple.init: invalid id " + id);
    }
    
    this.name = name;
  }
  
  private boolean validateId(int id) {
    return id > 0;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

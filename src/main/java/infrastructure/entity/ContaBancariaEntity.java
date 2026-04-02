package infrastructure.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * 
 */

@Entity
@DiscriminatorValue("BANCARIA")

public class ContaBancariaEntity extends ContaEntity{
}

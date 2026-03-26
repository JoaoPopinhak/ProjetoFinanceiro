package infrastructure.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * 
 */

@Entity
@DiscriminatorValue("INVESTIMENTO")

public class ContaInvestimentoEntity extends ContaEntity{
}

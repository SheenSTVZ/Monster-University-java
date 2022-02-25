package ec.edu.monster.model;

import ec.edu.monster.model.BealmAlmacn;
import ec.edu.monster.model.FeempEmple;
import ec.edu.monster.model.PecanCanton;
import ec.edu.monster.model.PeparParroPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:34:52")
@StaticMetamodel(PeparParro.class)
public class PeparParro_ { 

    public static volatile SingularAttribute<PeparParro, PeparParroPK> peparParroPK;
    public static volatile SingularAttribute<PeparParro, PecanCanton> pecanCanton;
    public static volatile CollectionAttribute<PeparParro, BealmAlmacn> bealmAlmacnCollection;
    public static volatile CollectionAttribute<PeparParro, FeempEmple> feempEmpleCollection;
    public static volatile SingularAttribute<PeparParro, String> peparDescri;

}
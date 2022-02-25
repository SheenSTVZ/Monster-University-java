package ec.edu.monster.model;

import ec.edu.monster.model.FeempEmple;
import ec.edu.monster.model.PecarCargoPK;
import ec.edu.monster.model.PedepDepar;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:34:52")
@StaticMetamodel(PecarCargo.class)
public class PecarCargo_ { 

    public static volatile SingularAttribute<PecarCargo, String> pecarDescri;
    public static volatile SingularAttribute<PecarCargo, PecarCargoPK> pecarCargoPK;
    public static volatile CollectionAttribute<PecarCargo, FeempEmple> feempEmpleCollection;
    public static volatile SingularAttribute<PecarCargo, PedepDepar> pedepDepar;

}
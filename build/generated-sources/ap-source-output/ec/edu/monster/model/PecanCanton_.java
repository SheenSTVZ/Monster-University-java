package ec.edu.monster.model;

import ec.edu.monster.model.PecanCantonPK;
import ec.edu.monster.model.PeparParro;
import ec.edu.monster.model.PeprovProvin;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:34:52")
@StaticMetamodel(PecanCanton.class)
public class PecanCanton_ { 

    public static volatile SingularAttribute<PecanCanton, PeprovProvin> peprovProvin;
    public static volatile SingularAttribute<PecanCanton, PecanCantonPK> pecanCantonPK;
    public static volatile CollectionAttribute<PecanCanton, PeparParro> peparParroCollection;
    public static volatile SingularAttribute<PecanCanton, String> pecanDescri;

}
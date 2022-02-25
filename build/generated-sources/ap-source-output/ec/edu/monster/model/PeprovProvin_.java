package ec.edu.monster.model;

import ec.edu.monster.model.PecanCanton;
import ec.edu.monster.model.PepaisPais;
import ec.edu.monster.model.PeprovProvinPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:34:52")
@StaticMetamodel(PeprovProvin.class)
public class PeprovProvin_ { 

    public static volatile SingularAttribute<PeprovProvin, PepaisPais> pepaisPais;
    public static volatile CollectionAttribute<PeprovProvin, PecanCanton> pecanCantonCollection;
    public static volatile SingularAttribute<PeprovProvin, String> peprovDescri;
    public static volatile SingularAttribute<PeprovProvin, PeprovProvinPK> peprovProvinPK;

}
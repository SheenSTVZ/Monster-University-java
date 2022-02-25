package ec.edu.monster.model;

import ec.edu.monster.model.BealmAlmacn;
import ec.edu.monster.model.BeartArticu;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:34:52")
@StaticMetamodel(BeinvInven.class)
public class BeinvInven_ { 

    public static volatile SingularAttribute<BeinvInven, String> beinvDescri;
    public static volatile SingularAttribute<BeinvInven, BealmAlmacn> bealmCodigo;
    public static volatile CollectionAttribute<BeinvInven, BeartArticu> beartArticuCollection;
    public static volatile SingularAttribute<BeinvInven, String> beinvCodigo;

}
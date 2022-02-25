package ec.edu.monster.model;

import ec.edu.monster.model.BeinvInven;
import ec.edu.monster.model.FeempEmple;
import ec.edu.monster.model.PeparParro;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:34:52")
@StaticMetamodel(BealmAlmacn.class)
public class BealmAlmacn_ { 

    public static volatile SingularAttribute<BealmAlmacn, String> bealmCodigo;
    public static volatile CollectionAttribute<BealmAlmacn, FeempEmple> feempEmpleCollection;
    public static volatile SingularAttribute<BealmAlmacn, String> bealmNombre;
    public static volatile CollectionAttribute<BealmAlmacn, BeinvInven> beinvInvenCollection;
    public static volatile SingularAttribute<BealmAlmacn, PeparParro> peparParro;
    public static volatile SingularAttribute<BealmAlmacn, String> bealmDescri;

}
package ec.edu.monster.model;

import ec.edu.monster.model.FecocCaor;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:34:52")
@StaticMetamodel(FeprvProv.class)
public class FeprvProv_ { 

    public static volatile SingularAttribute<FeprvProv, String> feprvCodigo3;
    public static volatile SingularAttribute<FeprvProv, String> beprvRazsoc;
    public static volatile CollectionAttribute<FeprvProv, FecocCaor> fecocCaorCollection;
    public static volatile SingularAttribute<FeprvProv, String> beprvObser;
    public static volatile SingularAttribute<FeprvProv, String> beprvDirec;
    public static volatile SingularAttribute<FeprvProv, String> beprvTelef;
    public static volatile SingularAttribute<FeprvProv, String> beprvRuc;

}
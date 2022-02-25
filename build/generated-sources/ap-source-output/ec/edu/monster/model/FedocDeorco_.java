package ec.edu.monster.model;

import ec.edu.monster.model.FeartArticu;
import ec.edu.monster.model.FecocCaor;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:34:52")
@StaticMetamodel(FedocDeorco.class)
public class FedocDeorco_ { 

    public static volatile SingularAttribute<FedocDeorco, BigDecimal> fedocSubtot;
    public static volatile SingularAttribute<FedocDeorco, BigDecimal> fedocCanti;
    public static volatile SingularAttribute<FedocDeorco, BigDecimal> fedocCoscom;
    public static volatile SingularAttribute<FedocDeorco, String> fecocNumero2;
    public static volatile SingularAttribute<FedocDeorco, FecocCaor> fecocCaor;
    public static volatile CollectionAttribute<FedocDeorco, FeartArticu> feartArticuCollection;

}
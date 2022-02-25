package ec.edu.monster.model;

import ec.edu.monster.model.FedocDeorco;
import ec.edu.monster.model.FeprvProv;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:34:52")
@StaticMetamodel(FecocCaor.class)
public class FecocCaor_ { 

    public static volatile SingularAttribute<FecocCaor, FeprvProv> feprvCodigo3;
    public static volatile SingularAttribute<FecocCaor, Date> fecocFecha;
    public static volatile SingularAttribute<FecocCaor, BigDecimal> fecocTotpag;
    public static volatile SingularAttribute<FecocCaor, BigDecimal> fecocTotal;
    public static volatile SingularAttribute<FecocCaor, BigDecimal> fecocDescu;
    public static volatile SingularAttribute<FecocCaor, FedocDeorco> fedocDeorco;
    public static volatile SingularAttribute<FecocCaor, String> fecocNumero2;
    public static volatile SingularAttribute<FecocCaor, BigDecimal> fecocIva;

}
package ec.edu.monster.model;

import ec.edu.monster.model.BeartArticu;
import ec.edu.monster.model.BeprvProv;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:34:52")
@StaticMetamodel(BeedaEntalm.class)
public class BeedaEntalm_ { 

    public static volatile SingularAttribute<BeedaEntalm, BigDecimal> beedaTotalbienes;
    public static volatile SingularAttribute<BeedaEntalm, BigDecimal> beedaValortotal;
    public static volatile SingularAttribute<BeedaEntalm, BeprvProv> beprvCodigo2;
    public static volatile CollectionAttribute<BeedaEntalm, BeartArticu> beartArticuCollection;
    public static volatile SingularAttribute<BeedaEntalm, Short> beedaNumero2;
    public static volatile SingularAttribute<BeedaEntalm, Short> beedaNumerofactura;
    public static volatile SingularAttribute<BeedaEntalm, Date> beedaFecha;

}
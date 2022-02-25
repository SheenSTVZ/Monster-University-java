package ec.edu.monster.model;

import ec.edu.monster.model.BeedaEntalm;
import ec.edu.monster.model.BeinvInven;
import ec.edu.monster.model.BesdaSalalm;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:34:52")
@StaticMetamodel(BeartArticu.class)
public class BeartArticu_ { 

    public static volatile SingularAttribute<BeartArticu, String> feartCodigo;
    public static volatile SingularAttribute<BeartArticu, String> feartDescri;
    public static volatile CollectionAttribute<BeartArticu, BesdaSalalm> besdaSalalmCollection;
    public static volatile SingularAttribute<BeartArticu, BigDecimal> feartStock;
    public static volatile SingularAttribute<BeartArticu, BeedaEntalm> beedaNumero2;
    public static volatile SingularAttribute<BeartArticu, BigDecimal> feartPrecom;
    public static volatile SingularAttribute<BeartArticu, String> feartObser;
    public static volatile SingularAttribute<BeartArticu, BeinvInven> beinvCodigo;

}
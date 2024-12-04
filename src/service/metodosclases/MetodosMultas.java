package service.metodosclases;

import model.Coche;
import model.Multa;
import service.crud.HibernateCrud;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MetodosMultas {

    public List<Multa> getMultasList(List<Integer> idCoches){
        List<Multa> multas = new ArrayList<>();
        List<Coche> cocheList = auxGetCochesList(idCoches);
        auxInsetMultas(multas, cocheList);
        return multas;
    }

    private void auxInsetMultas(List<Multa> multaList, List<Coche> cocheList){
        Multa multa1 = new Multa(cocheList.get(0), BigDecimal.valueOf(100.50), 10);
        Multa multa2 = new Multa(cocheList.get(1), BigDecimal.valueOf(150.00), 5);
        Multa multa3 = new Multa(cocheList.get(0), BigDecimal.valueOf(200.00), 15);
        Multa multa4 = new Multa(cocheList.get(1), BigDecimal.valueOf(50.75), 0);

        multaList.add(multa1);
        multaList.add(multa2);
        multaList.add(multa3);
        multaList.add(multa4);
    }

    private List<Coche> auxGetCochesList(List<Integer> idCoches) {
        HibernateCrud crud = new HibernateCrud();
        List<Coche> cochesList = new ArrayList<>();

        for(Integer idCoche : idCoches){
            cochesList.add(crud.getCocheFromDb(idCoche));
        }
        return cochesList;
    }
}



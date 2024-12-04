package controller;

import model.Multa;
import service.crud.HibernateCrud;
import service.metodosclases.MetodosCoches;
import service.metodosclases.MetodosMultas;
import service.xml.LecturaEscrituraXml;

import java.math.BigDecimal;
import java.util.List;

public class Controller {

    public void logicaPrograma(){

        HibernateCrud crud = new HibernateCrud();
        MetodosCoches metodosCoches = new MetodosCoches();
        MetodosMultas metodosMultas = new MetodosMultas();
        LecturaEscrituraXml leXML = new LecturaEscrituraXml();


        crud.insertIntoCochesDB(metodosCoches.getCochesList());
        List<Integer> idCoches = crud.selectIdCoches();
        printIdsCoches(idCoches);

        crud.insertIntoMultasDB(metodosMultas.getMultasList(idCoches));


        leXML.writeXmlMulta(crud.selectMultasFromDb(), "multas.xml");
        System.out.println("REBAIXA POLICIAL");


        updateMultas(crud, 25);

        printPrezoPrimeraMulta(crud);

        updateMultasSenSuma(crud, leXML);

        System.out.println("FIN REBAIXA POLICIAL");

        crud.deleteDatosDB("DELETE FROM Multa");
        crud.deleteDatosDB("DELETE FROM Coche");



    }

    private void printIdsCoches(List<Integer> isCochesList){
        System.out.println("Ids de los coches: ");
        for(Integer id : isCochesList){
            System.out.println(id);
        }
    }

    private void printPrezoPrimeraMulta(HibernateCrud crud){
        System.out.println("El precio actual de la priemra multa es de: " + formula(crud) + "€");
    }



    private void updateMultas(HibernateCrud crud, Integer newPorcentaxeReduccion){
        for(int i = 1; i < 5; i++){
            crud.updateMultas(i, newPorcentaxeReduccion);
        }
    }

    private void updateMultasSenSuma(HibernateCrud crud, LecturaEscrituraXml leXML){
        List<Integer> porcentaxeReduccionList = leXML.getMultasList("multas.xml");
        List<Integer> multasIdList = crud.selectIdMultas();
        for(int i = 0; i < multasIdList.size(); i++){
            crud.updateMultasSinSuma(multasIdList.get(i), porcentaxeReduccionList.get(i));
        }
    }

    private int formula(HibernateCrud crud){
        List<Multa> multaList = crud.selectMultasFromDb();

        Multa multa1 = multaList.get(0);

        BigDecimal importe = multa1.getImporte();
        BigDecimal porcentaxeOferta = BigDecimal.valueOf(multa1.getPorcentaxereduccion());

        BigDecimal descuento = porcentaxeOferta.divide(BigDecimal.valueOf(100));

        return importe.multiply(BigDecimal.ONE.subtract(descuento)).intValue();
    }

}


/*
 BigDecimal prezoTenda = inventariotenda.getPrezotenda();
        BigDecimal porcentaxeOferta = BigDecimal.valueOf(inventariotenda.getPorcentaxeoferta());

        BigDecimal factorDesconto = porcentaxeOferta.divide(BigDecimal.valueOf(100));


        return prezoTenda.multiply(BigDecimal.ONE.subtract(factorDesconto));
 */
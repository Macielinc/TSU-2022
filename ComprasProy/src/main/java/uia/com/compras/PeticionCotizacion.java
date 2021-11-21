package uia.com.compras;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class PeticionCotizacion extends PeticionOrdenCompra {
    private int valorUnitario;
    private int subtotalItem;
    private int totalCotizacion;

    @JsonCreator
    public PeticionCotizacion(@JsonProperty("id")int id, @JsonProperty("name")String name,
                               @JsonProperty("codigo")String codigo, @JsonProperty("unidad")String unidad,
                               @JsonProperty("cantidad")int cantidad,@JsonProperty("valorUnitario")int valorUnitario, @JsonProperty("subtotalItem")int subtotalItem,
                               @JsonProperty("totalCotizacion")int totalCotizacion)
    {
        super(id, name,codigo,unidad,cantidad);
        this.valorUnitario = valorUnitario;
        this.subtotalItem = subtotalItem;
        this.totalCotizacion = totalCotizacion;
    }




    public int getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(int valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getSubtotalItem() {
        subtotalItem = valorUnitario * getCantidad();
        return subtotalItem;
    }

    public void setSubtotalItem(int subtotalItem) {
        this.subtotalItem = subtotalItem;
    }

    public int getTotalCotizacion() {
        totalCotizacion += subtotalItem;
        return totalCotizacion;
    }

    public void setTotalCotizacion(int totalCotizacion) {
        this.totalCotizacion = totalCotizacion;
    }
    public PeticionCotizacion() {
        super(-1, "");
    }

    public void agregaItems(ListaReportesNivelStock miReporteNS)
    {
        PeticionCotizacion nodo;
        for(int i=0; i<miReporteNS.getItems().size(); i++)
        {
            InfoComprasUIA miNodo = miReporteNS.getItems().get(i);
            List<InfoComprasUIA> miLista;
            if(miNodo.getPedidoProveedor() > 0)
            {
                nodo = new PeticionCotizacion(miNodo.getId(), miNodo.getName(), miNodo.getDescripcion(),
                        "PZA", miNodo.getPedidoProveedor(),getValorUnitario(),getSubtotalItem(),getTotalCotizacion());
                if(this.getItems() == null)
                {
                    miLista = new ArrayList<InfoComprasUIA>();
                    this.setItems((List<InfoComprasUIA>) miLista);
                }
                this.getItems().add(nodo);
            }
            miNodo.print();
        }

    }
}

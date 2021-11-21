package uia.com.compras;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitudCotizacion extends  PeticionCotizacion{
    private int cotizacion =-1;

    @JsonCreator
    public SolicitudCotizacion(@JsonProperty("id")int id, @JsonProperty("name")String name,
                                @JsonProperty("codigo")String codigo, @JsonProperty("unidad")String unidad,
                                @JsonProperty("cantidad")int cantidad, @JsonProperty("cotizacion")int cotizacion,
                                @JsonProperty("codigo")int valorUnitario, @JsonProperty("unidad")int subtotalItem,
                                @JsonProperty("cantidad")int totalCotizacion,@JsonProperty("clasificacionCotizacion")int clasificacionCotizacion)
    {
        super(id, name, codigo, unidad, cantidad, valorUnitario,subtotalItem,totalCotizacion);
        this.cotizacion = cotizacion;
    }
    public SolicitudCotizacion(PeticionCotizacion info)
    {
        super(info.getId(), info.getName(), info.getCodigo(), info.getUnidad(), info.getCantidad(),info.getValorUnitario(),info.getSubtotalItem(), info.getTotalCotizacion());
        this.setClasificacion(info.getClasificacion());
    }

    public SolicitudCotizacion() {

    }

    public int getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(int cotizacion) {
        this.cotizacion = cotizacion;
    }
}

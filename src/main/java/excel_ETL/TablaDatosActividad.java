package excel_ETL;

import java.util.ArrayList;
import java.util.List;

public class TablaDatosActividad {
  private List<DatosDeLaActividad> listaDatosActividad;

  public TablaDatosActividad() {
    this.listaDatosActividad = new ArrayList();
  }

  public List<DatosDeLaActividad> getListaDatosActividad() {
    return listaDatosActividad;
  }
}

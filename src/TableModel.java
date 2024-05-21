import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel {
    private List<Projekt> seznam;

    public TableModel(List<Projekt> seznam) {
        this.seznam = seznam; // Inicializace seznamu správně
    }

    @Override
    public int getRowCount() {
        return seznam.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Projekt projekt = seznam.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return projekt.getNazev();
            case 1:
                return projekt.getPocetResitelu();
            case 2:
                return projekt.getNaklady();
            case 3:
                return projekt.getHodnoceni();
            case 4:
                return projekt.getDatum();
            case 5:
                return projekt.getDokonceno();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Název";
            case 1:
                return "Počet Řešitelů";
            case 2:
                return "Náklady";
            case 3:
                return "Hodnocení";
            case 4:
                return "Datum";
            case 5:
                return "Dokončeno";
            default:
                return super.getColumnName(column);
        }
    }
}

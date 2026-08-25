package Pannelli;

import DAO.PagamentoDAO;
import DAO.VenditaDAO;
import Item.Piatto;
import Utility.Pagamento;
import Utility.Vendita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class PannelloStorico extends JPanel {

    private VenditaDAO venditaDAO;
    private PagamentoDAO pagamentoDAO;
    private DefaultTableModel model;

    public PannelloStorico() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] colonne = {"ID", "Operatore", "Ordine", "Pagamento", "Totale"};
        model = new DefaultTableModel(colonne, 0){
            public boolean isCellEditable(int r, int c){
                return false;
            }
        };
        venditaDAO = new VenditaDAO();
        pagamentoDAO = new PagamentoDAO();
        double incasso = 0;
        for (Vendita v : venditaDAO.findAll()){
            Pagamento p = pagamentoDAO.findByVendita(v.getId());
            StringBuilder sb = new StringBuilder();
            for(Map.Entry<Piatto,Integer> entry : v.getProdotti().entrySet()){
                if (!sb.isEmpty()){
                    sb.append(", ");
                }
                sb.append(entry.getValue());
                sb.append("x ");
                sb.append(entry.getKey().getNome());
            }
            if(p!= null){
                incasso += p.getImporto();
                model.addRow(new Object[]{
                        v.getId(),
                        v.getOperatore(),
                        sb.toString(),
                        p.getMetodoPagamento(),
                        String.format("€ %.2f",p.getImporto())
                });
            }
        }
        JLabel incassoLabel = new JLabel(String.format("Incasso: € %.2f", incasso));
        incassoLabel.setFont(incassoLabel.getFont().deriveFont(Font.BOLD));
        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        add(incassoLabel, BorderLayout.SOUTH);
    }
}

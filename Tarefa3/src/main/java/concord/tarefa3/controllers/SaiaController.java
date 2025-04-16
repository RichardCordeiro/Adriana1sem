package concord.tarefa3.controllers;

import concord.tarefa3.models.Saia;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class SaiaController {
    @FXML private TextField txtCor;
    @FXML private TextField txtTecido;
    @FXML private TextField txtComprimento;
    @FXML private CheckBox chkPlissada;
    @FXML private TextArea textResultado;

    private List<Saia> listaSaias = new ArrayList<>();

    @FXML
    public void criarSaia() {
        if (txtCor.getText().isEmpty() || txtTecido.getText().isEmpty() || txtComprimento.getText().isEmpty()) {
            textResultado.setText("Preencha todos os campos.");
            return;
        }

        try {
            double comprimento = Double.parseDouble(txtComprimento.getText());
            Saia saiaAtual = new Saia(txtCor.getText(), txtTecido.getText(), comprimento, chkPlissada.isSelected());
            listaSaias.add(saiaAtual);  
            textResultado.setText("Saia criada:\n" + formatarSaia(saiaAtual));
        } catch (NumberFormatException e) {
            textResultado.setText("Digite um número válido para comprimento.");
        }
    }

    @FXML
    public void lavarSaia() {
        if (listaSaias.isEmpty()) {
            textResultado.setText("Nenhuma saia foi criada ainda.");
            return;
        }

        
        Saia saiaAtual = listaSaias.get(listaSaias.size() - 1);
        textResultado.setText("Saia lavada:\n" + formatarSaia(saiaAtual));
    }

    @FXML
    public void vestirSaia() {
        if (listaSaias.isEmpty()) {
            textResultado.setText("Nenhuma saia foi criada ainda.");
            return;
        }

        
        Saia saiaAtual = listaSaias.get(listaSaias.size() - 1);
        textResultado.setText("Saia vestida:\n" + formatarSaia(saiaAtual));
    }

    private String formatarSaia(Saia saia) {
        return "Cor: " + saia.getCor() + "\n" +
                "Tecido: " + saia.getTecido() + "\n" +
                "Comprimento: " + saia.getComprimento() + " cm\n" +
                "Plissada: " + (saia.isPlissada() ? "Sim" : "Não");
    }
}

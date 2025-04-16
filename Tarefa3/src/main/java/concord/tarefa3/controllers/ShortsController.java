package concord.tarefa3.controllers;

import concord.tarefa3.models.Shorts;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class ShortsController {
    @FXML private TextField txtTecido;
    @FXML private TextField txtCor;
    @FXML private TextField txtTamanho;
    @FXML private CheckBox chkBolsos;
    @FXML private TextArea textResultado;

    private List<Shorts> listaShorts = new ArrayList<>();

    @FXML
    public void criarShorts() {
        if (txtTecido.getText().isEmpty() || txtCor.getText().isEmpty() || txtTamanho.getText().isEmpty()) {
            textResultado.setText("Preencha todos os campos.");
            return;
        }

        Shorts shortsAtual = new Shorts(txtTecido.getText(), txtCor.getText(), txtTamanho.getText(), chkBolsos.isSelected());
        listaShorts.add(shortsAtual);  
        textResultado.setText("Shorts criado:\n" + formatarShorts(shortsAtual));
    }

    @FXML
    public void guardarShorts() {
        if (listaShorts.isEmpty()) {
            textResultado.setText("Nenhum shorts foi criado ainda.");
            return;
        }

        
        Shorts shortsAtual = listaShorts.get(listaShorts.size() - 1);
        textResultado.setText("Shorts guardados:\n" + formatarShorts(shortsAtual));
    }

    @FXML
    public void vestirShorts() {
        if (listaShorts.isEmpty()) {
            textResultado.setText("Nenhum shorts foi criado ainda.");
            return;
        }

        
        Shorts shortsAtual = listaShorts.get(listaShorts.size() - 1);
        textResultado.setText("Shorts vestidos:\n" + formatarShorts(shortsAtual));
    }

    private String formatarShorts(Shorts shorts) {
        return "Tecido: " + shorts.getTecido() + "\n" +
                "Cor: " + shorts.getCor() + "\n" +
                "Tamanho: " + shorts.getTamanho() + "\n" +
                "Tem bolsos: " + (shorts.isBolsos() ? "Sim" : "Não");
    }
}

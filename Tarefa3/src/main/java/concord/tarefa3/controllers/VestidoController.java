package concord.tarefa3.controllers;

import concord.tarefa3.models.Vestido;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class VestidoController {
    @FXML private TextField txtCor;
    @FXML private TextField txtTamanho;
    @FXML private TextField txtTecido;
    @FXML private CheckBox chkEstampa;
    @FXML private TextArea textResultado;

    private List<Vestido> listaVestidos = new ArrayList<>();

    @FXML
    public void criarVestido() {
        if (txtCor.getText().isEmpty() || txtTamanho.getText().isEmpty() || txtTecido.getText().isEmpty()) {
            textResultado.setText("Preencha todos os campos.");
            return;
        }

        Vestido vestidoAtual = new Vestido(txtCor.getText(), txtTamanho.getText(), txtTecido.getText(), chkEstampa.isSelected());
        listaVestidos.add(vestidoAtual);  
        textResultado.setText("Vestido criado:\n" + formatarVestido(vestidoAtual));
    }

    @FXML
    public void lavarVestido() {
        if (listaVestidos.isEmpty()) {
            textResultado.setText("Nenhum vestido foi criado ainda.");
            return;
        }

        
        Vestido vestidoAtual = listaVestidos.get(listaVestidos.size() - 1);
        textResultado.setText("Vestido lavado:\n" + formatarVestido(vestidoAtual));
    }

    @FXML
    public void vestirVestido() {
        if (listaVestidos.isEmpty()) {
            textResultado.setText("Nenhum vestido foi criado ainda.");
            return;
        }

        
        Vestido vestidoAtual = listaVestidos.get(listaVestidos.size() - 1);
        textResultado.setText("Vestido vestido:\n" + formatarVestido(vestidoAtual));
    }

    private String formatarVestido(Vestido vestido) {
        return "Cor: " + vestido.getCor() + "\n" +
                "Tamanho: " + vestido.getTamanho() + "\n" +
                "Tecido: " + vestido.getTecido() + "\n" +
                "Tem estampa: " + (vestido.isTemEstampa() ? "Sim" : "Não");
    }
}

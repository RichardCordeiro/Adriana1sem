package concord.tarefa3.controllers;

import concord.tarefa3.models.Jaqueta;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class JaquetaController {
    @FXML private TextField txtModelo;
    @FXML private TextField txtCor;
    @FXML private TextField txtMaterial;
    @FXML private CheckBox chkImpermeavel;
    @FXML private TextArea textResultado;

    private List<Jaqueta> listaJaquetas = new ArrayList<>();

    @FXML
    public void criarJaqueta() {
        String modelo = txtModelo.getText();
        String cor = txtCor.getText();
        String material = txtMaterial.getText();
        boolean impermeavel = chkImpermeavel.isSelected();

        if (modelo.isEmpty() || cor.isEmpty() || material.isEmpty()) {
            textResultado.setText("Preencha todos os campos.");
            return;
        }

        Jaqueta jaquetaAtual = new Jaqueta(modelo, cor, material, impermeavel);
        listaJaquetas.add(jaquetaAtual);  
        textResultado.setText("Jaqueta criada:\n" + formatarJaqueta(jaquetaAtual));
    }

    @FXML
    public void dobrarJaqueta() {
        if (listaJaquetas.isEmpty()) {
            textResultado.setText("Nenhuma jaqueta foi cadastrada ainda.");
            return;
        }

        
        Jaqueta jaquetaAtual = listaJaquetas.get(listaJaquetas.size() - 1);
        textResultado.setText("Jaqueta dobrada:\n" + formatarJaqueta(jaquetaAtual));
    }

    @FXML
    public void vestirJaqueta() {
        if (listaJaquetas.isEmpty()) {
            textResultado.setText("Nenhuma jaqueta foi cadastrada ainda.");
            return;
        }

        
        Jaqueta jaquetaAtual = listaJaquetas.get(listaJaquetas.size() - 1);
        textResultado.setText("Jaqueta vestida:\n" + formatarJaqueta(jaquetaAtual));
    }

    private String formatarJaqueta(Jaqueta jaqueta) {
        return "Modelo: " + jaqueta.getModelo() + "\n" +
                "Cor: " + jaqueta.getCor() + "\n" +
                "Material: " + jaqueta.getMaterial() + "\n" +
                "Impermeável: " + (jaqueta.isImpermeavel() ? "Sim" : "Não");
    }
}

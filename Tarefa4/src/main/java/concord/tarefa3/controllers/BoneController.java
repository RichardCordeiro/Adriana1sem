package concord.tarefa3.controllers;

import concord.tarefa3.dao.BoneDAO;
import concord.tarefa3.models.Bone;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class BoneController {
    @FXML private TextField txtCor;
    @FXML private TextField txtEstilo;
    @FXML private TextField txtTamanho;
    @FXML private TextField txtMarca;
    @FXML private TextArea txtResultado;
    @FXML private TableView<Bone> tableBones;
    @FXML private TableColumn<Bone, Integer> colId;
    @FXML private TableColumn<Bone, String> colCor;
    @FXML private TableColumn<Bone, String> colEstilo;
    @FXML private TableColumn<Bone, String> colTamanho;
    @FXML private TableColumn<Bone, String> colMarca;

    private BoneDAO boneDAO;
    private ObservableList<Bone> bones;

    @FXML
    public void initialize() {
        boneDAO = new BoneDAO();
        bones = FXCollections.observableArrayList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colEstilo.setCellValueFactory(new PropertyValueFactory<>("estilo"));
        colTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        
        tableBones.setItems(bones);
        
        tableBones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                preencherCampos(newSelection);
            }
        });
        
        atualizarLista();
    }

    @FXML
    private void criarBone() {
        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            Bone bone = new Bone();
            bone.setCor(txtCor.getText());
            bone.setEstilo(txtEstilo.getText());
            bone.setTamanho(txtTamanho.getText());
            bone.setMarca(txtMarca.getText());

            boneDAO.criar(bone);
            mostrarMensagem("Boné criado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao criar boné: " + e.getMessage());
        }
    }

    @FXML
    private void listarBones() {
        atualizarLista();
        mostrarMensagem("Lista de bonés atualizada!");
    }

    @FXML
    private void atualizarBone() {
        Bone boneSelecionado = tableBones.getSelectionModel().getSelectedItem();
        if (boneSelecionado == null) {
            mostrarMensagem("Por favor, selecione um boné para atualizar.");
            return;
        }

        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            boneSelecionado.setCor(txtCor.getText());
            boneSelecionado.setEstilo(txtEstilo.getText());
            boneSelecionado.setTamanho(txtTamanho.getText());
            boneSelecionado.setMarca(txtMarca.getText());

            boneDAO.atualizar(boneSelecionado);
            mostrarMensagem("Boné atualizado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao atualizar boné: " + e.getMessage());
        }
    }

    @FXML
    private void deletarBone() {
        Bone boneSelecionado = tableBones.getSelectionModel().getSelectedItem();
        if (boneSelecionado == null) {
            mostrarMensagem("Por favor, selecione um boné para deletar.");
            return;
        }

        try {
            boneDAO.deletar(boneSelecionado.getId());
            mostrarMensagem("Boné deletado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao deletar boné: " + e.getMessage());
        }
    }

    private void atualizarLista() {
        bones.clear();
        bones.addAll(boneDAO.listarTodos());
    }

    private void preencherCampos(Bone bone) {
        txtCor.setText(bone.getCor());
        txtEstilo.setText(bone.getEstilo());
        txtTamanho.setText(bone.getTamanho());
        txtMarca.setText(bone.getMarca());
        txtResultado.setText(formatarBone(bone));
    }

    private String formatarBone(Bone bone) {
        return String.format("ID: %d\nCor: %s\nEstilo: %s\nTamanho: %s\nMarca: %s",
            bone.getId(),
            bone.getCor(),
            bone.getEstilo(),
            bone.getTamanho(),
            bone.getMarca()
        );
    }

    private boolean camposVazios() {
        return txtCor.getText().isEmpty() ||
               txtEstilo.getText().isEmpty() ||
               txtTamanho.getText().isEmpty() ||
               txtMarca.getText().isEmpty();
    }

    private void limparCampos() {
        txtCor.clear();
        txtEstilo.clear();
        txtTamanho.clear();
        txtMarca.clear();
        txtResultado.clear();
        tableBones.getSelectionModel().clearSelection();
    }

    private void mostrarMensagem(String mensagem) {
        txtResultado.setText(mensagem);
    }
}

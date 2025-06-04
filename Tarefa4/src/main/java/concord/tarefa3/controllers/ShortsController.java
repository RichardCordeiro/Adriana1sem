package concord.tarefa3.controllers;

import concord.tarefa3.dao.ShortsDAO;
import concord.tarefa3.models.Shorts;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShortsController {
    @FXML private TextField txtCor;
    @FXML private TextField txtTamanho;
    @FXML private TextField txtTecido;
    @FXML private TextField txtMarca;
    @FXML private TextArea txtResultado;
    @FXML private TableView<Shorts> tableShorts;
    @FXML private TableColumn<Shorts, Integer> colId;
    @FXML private TableColumn<Shorts, String> colCor;
    @FXML private TableColumn<Shorts, String> colTamanho;
    @FXML private TableColumn<Shorts, String> colTecido;
    @FXML private TableColumn<Shorts, String> colMarca;

    private ShortsDAO shortsDAO;
    private ObservableList<Shorts> shortsList;

    @FXML
    public void initialize() {
        shortsDAO = new ShortsDAO();
        shortsList = FXCollections.observableArrayList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        colTecido.setCellValueFactory(new PropertyValueFactory<>("tecido"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        
        tableShorts.setItems(shortsList);
        
        tableShorts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                preencherCampos(newSelection);
            }
        });
        
        atualizarLista();
    }

    @FXML
    private void criarShorts() {
        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            Shorts shorts = new Shorts();
            shorts.setCor(txtCor.getText());
            shorts.setTamanho(txtTamanho.getText());
            shorts.setTecido(txtTecido.getText());
            shorts.setMarca(txtMarca.getText());

            shortsDAO.criar(shorts);
            mostrarMensagem("Shorts criado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao criar shorts: " + e.getMessage());
        }
    }

    @FXML
    private void listarShorts() {
        atualizarLista();
        mostrarMensagem("Lista de shorts atualizada!");
    }

    @FXML
    private void atualizarShorts() {
        Shorts shortsSelecionado = tableShorts.getSelectionModel().getSelectedItem();
        if (shortsSelecionado == null) {
            mostrarMensagem("Por favor, selecione um shorts para atualizar.");
            return;
        }

        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            shortsSelecionado.setCor(txtCor.getText());
            shortsSelecionado.setTamanho(txtTamanho.getText());
            shortsSelecionado.setTecido(txtTecido.getText());
            shortsSelecionado.setMarca(txtMarca.getText());

            shortsDAO.atualizar(shortsSelecionado);
            mostrarMensagem("Shorts atualizado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao atualizar shorts: " + e.getMessage());
        }
    }

    @FXML
    private void deletarShorts() {
        Shorts shortsSelecionado = tableShorts.getSelectionModel().getSelectedItem();
        if (shortsSelecionado == null) {
            mostrarMensagem("Por favor, selecione um shorts para deletar.");
            return;
        }

        try {
            shortsDAO.deletar(shortsSelecionado.getId());
            mostrarMensagem("Shorts deletado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao deletar shorts: " + e.getMessage());
        }
    }

    private void atualizarLista() {
        shortsList.clear();
        shortsList.addAll(shortsDAO.listarTodos());
    }

    private void preencherCampos(Shorts shorts) {
        txtCor.setText(shorts.getCor());
        txtTamanho.setText(shorts.getTamanho());
        txtTecido.setText(shorts.getTecido());
        txtMarca.setText(shorts.getMarca());
        txtResultado.setText(formatarShorts(shorts));
    }

    private String formatarShorts(Shorts shorts) {
        return String.format("ID: %d\nCor: %s\nTamanho: %s\nTecido: %s\nMarca: %s",
            shorts.getId(),
            shorts.getCor(),
            shorts.getTamanho(),
            shorts.getTecido(),
            shorts.getMarca()
        );
    }

    private boolean camposVazios() {
        return txtCor.getText().isEmpty() ||
               txtTamanho.getText().isEmpty() ||
               txtTecido.getText().isEmpty() ||
               txtMarca.getText().isEmpty();
    }

    private void limparCampos() {
        txtCor.clear();
        txtTamanho.clear();
        txtTecido.clear();
        txtMarca.clear();
        txtResultado.clear();
        tableShorts.getSelectionModel().clearSelection();
    }

    private void mostrarMensagem(String mensagem) {
        txtResultado.setText(mensagem);
    }
}

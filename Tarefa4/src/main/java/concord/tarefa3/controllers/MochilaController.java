package concord.tarefa3.controllers;

import concord.tarefa3.dao.MochilaDAO;
import concord.tarefa3.models.Mochila;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class MochilaController {
    @FXML private TextField txtMarca;
    @FXML private TextField txtLitros;
    @FXML private TextField txtCor;
    @FXML private CheckBox chkCompartimentoNotebook;
    @FXML private TextArea txtResultado;
    @FXML private TableView<Mochila> tableMochilas;
    @FXML private TableColumn<Mochila, Integer> colId;
    @FXML private TableColumn<Mochila, String> colMarca;
    @FXML private TableColumn<Mochila, Integer> colLitros;
    @FXML private TableColumn<Mochila, String> colCor;
    @FXML private TableColumn<Mochila, Boolean> colCompartimentoNotebook;

    private MochilaDAO mochilaDAO;
    private ObservableList<Mochila> mochilasList;

    @FXML
    public void initialize() {
        mochilaDAO = new MochilaDAO();
        mochilasList = FXCollections.observableArrayList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colLitros.setCellValueFactory(new PropertyValueFactory<>("litros"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colCompartimentoNotebook.setCellValueFactory(new PropertyValueFactory<>("temCompartimentoNotebook"));
        
        tableMochilas.setItems(mochilasList);
        
        tableMochilas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                preencherCampos(newSelection);
            }
        });
        
        atualizarLista();
    }

    @FXML
    private void criarMochila() {
        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            Mochila mochila = new Mochila();
            mochila.setMarca(txtMarca.getText());
            mochila.setLitros(Integer.parseInt(txtLitros.getText()));
            mochila.setCor(txtCor.getText());
            mochila.setTemCompartimentoNotebook(chkCompartimentoNotebook.isSelected());

            mochilaDAO.criar(mochila);
            mostrarMensagem("Mochila criada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (NumberFormatException e) {
            mostrarMensagem("Por favor, insira um valor numérico válido para os litros.");
        } catch (Exception e) {
            mostrarMensagem("Erro ao criar mochila: " + e.getMessage());
        }
    }

    @FXML
    private void listarMochilas() {
        atualizarLista();
        mostrarMensagem("Lista de mochilas atualizada!");
    }

    @FXML
    private void atualizarMochila() {
        Mochila mochilaSelecionada = tableMochilas.getSelectionModel().getSelectedItem();
        if (mochilaSelecionada == null) {
            mostrarMensagem("Por favor, selecione uma mochila para atualizar.");
            return;
        }

        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            mochilaSelecionada.setMarca(txtMarca.getText());
            mochilaSelecionada.setLitros(Integer.parseInt(txtLitros.getText()));
            mochilaSelecionada.setCor(txtCor.getText());
            mochilaSelecionada.setTemCompartimentoNotebook(chkCompartimentoNotebook.isSelected());

            mochilaDAO.atualizar(mochilaSelecionada);
            mostrarMensagem("Mochila atualizada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (NumberFormatException e) {
            mostrarMensagem("Por favor, insira um valor numérico válido para os litros.");
        } catch (Exception e) {
            mostrarMensagem("Erro ao atualizar mochila: " + e.getMessage());
        }
    }

    @FXML
    private void deletarMochila() {
        Mochila mochilaSelecionada = tableMochilas.getSelectionModel().getSelectedItem();
        if (mochilaSelecionada == null) {
            mostrarMensagem("Por favor, selecione uma mochila para deletar.");
            return;
        }

        try {
            mochilaDAO.deletar(mochilaSelecionada.getId());
            mostrarMensagem("Mochila deletada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao deletar mochila: " + e.getMessage());
        }
    }

    private void atualizarLista() {
        mochilasList.clear();
        mochilasList.addAll(mochilaDAO.listarTodos());
    }

    private void preencherCampos(Mochila mochila) {
        txtMarca.setText(mochila.getMarca());
        txtLitros.setText(String.valueOf(mochila.getLitros()));
        txtCor.setText(mochila.getCor());
        chkCompartimentoNotebook.setSelected(mochila.isTemCompartimentoNotebook());
        txtResultado.setText(formatarMochila(mochila));
    }

    private String formatarMochila(Mochila mochila) {
        return String.format("ID: %d\nMarca: %s\nLitros: %d\nCor: %s\nCompartimento para Notebook: %s",
            mochila.getId(),
            mochila.getMarca(),
            mochila.getLitros(),
            mochila.getCor(),
            mochila.isTemCompartimentoNotebook() ? "Sim" : "Não"
        );
    }

    private boolean camposVazios() {
        return txtMarca.getText().isEmpty() ||
               txtLitros.getText().isEmpty() ||
               txtCor.getText().isEmpty();
    }

    private void limparCampos() {
        txtMarca.clear();
        txtLitros.clear();
        txtCor.clear();
        chkCompartimentoNotebook.setSelected(false);
        txtResultado.clear();
        tableMochilas.getSelectionModel().clearSelection();
    }

    private void mostrarMensagem(String mensagem) {
        txtResultado.setText(mensagem);
    }
}

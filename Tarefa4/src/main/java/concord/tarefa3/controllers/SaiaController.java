package concord.tarefa3.controllers;

import concord.tarefa3.dao.SaiaDAO;
import concord.tarefa3.models.Saia;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class SaiaController {
    @FXML private TextField txtCor;
    @FXML private TextField txtTecido;
    @FXML private TextField txtComprimento;
    @FXML private CheckBox chkPlissada;
    @FXML private TextArea txtResultado;
    @FXML private TableView<Saia> tableSaias;
    @FXML private TableColumn<Saia, Integer> colId;
    @FXML private TableColumn<Saia, String> colCor;
    @FXML private TableColumn<Saia, String> colTecido;
    @FXML private TableColumn<Saia, Double> colComprimento;
    @FXML private TableColumn<Saia, Boolean> colPlissada;

    private SaiaDAO saiaDAO;
    private ObservableList<Saia> saiasList;

    @FXML
    public void initialize() {
        saiaDAO = new SaiaDAO();
        saiasList = FXCollections.observableArrayList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colTecido.setCellValueFactory(new PropertyValueFactory<>("tecido"));
        colComprimento.setCellValueFactory(new PropertyValueFactory<>("comprimento"));
        colPlissada.setCellValueFactory(new PropertyValueFactory<>("plissada"));
        
        tableSaias.setItems(saiasList);
        
        tableSaias.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                preencherCampos(newSelection);
            }
        });
        
        atualizarLista();
    }

    @FXML
    private void criarSaia() {
        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            Saia saia = new Saia();
            saia.setCor(txtCor.getText());
            saia.setTecido(txtTecido.getText());
            saia.setComprimento(Double.parseDouble(txtComprimento.getText()));
            saia.setPlissada(chkPlissada.isSelected());

            saiaDAO.criar(saia);
            mostrarMensagem("Saia criada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (NumberFormatException e) {
            mostrarMensagem("Por favor, insira um valor numérico válido para o comprimento.");
        } catch (Exception e) {
            mostrarMensagem("Erro ao criar saia: " + e.getMessage());
        }
    }

    @FXML
    private void listarSaias() {
        atualizarLista();
        mostrarMensagem("Lista de saias atualizada!");
    }

    @FXML
    private void atualizarSaia() {
        Saia saiaSelecionada = tableSaias.getSelectionModel().getSelectedItem();
        if (saiaSelecionada == null) {
            mostrarMensagem("Por favor, selecione uma saia para atualizar.");
            return;
        }

        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            saiaSelecionada.setCor(txtCor.getText());
            saiaSelecionada.setTecido(txtTecido.getText());
            saiaSelecionada.setComprimento(Double.parseDouble(txtComprimento.getText()));
            saiaSelecionada.setPlissada(chkPlissada.isSelected());

            saiaDAO.atualizar(saiaSelecionada);
            mostrarMensagem("Saia atualizada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (NumberFormatException e) {
            mostrarMensagem("Por favor, insira um valor numérico válido para o comprimento.");
        } catch (Exception e) {
            mostrarMensagem("Erro ao atualizar saia: " + e.getMessage());
        }
    }

    @FXML
    private void deletarSaia() {
        Saia saiaSelecionada = tableSaias.getSelectionModel().getSelectedItem();
        if (saiaSelecionada == null) {
            mostrarMensagem("Por favor, selecione uma saia para deletar.");
            return;
        }

        try {
            saiaDAO.deletar(saiaSelecionada.getId());
            mostrarMensagem("Saia deletada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao deletar saia: " + e.getMessage());
        }
    }

    private void atualizarLista() {
        saiasList.clear();
        saiasList.addAll(saiaDAO.listarTodos());
    }

    private void preencherCampos(Saia saia) {
        txtCor.setText(saia.getCor());
        txtTecido.setText(saia.getTecido());
        txtComprimento.setText(String.valueOf(saia.getComprimento()));
        chkPlissada.setSelected(saia.isPlissada());
        txtResultado.setText(formatarSaia(saia));
    }

    private String formatarSaia(Saia saia) {
        return String.format("ID: %d\nCor: %s\nTecido: %s\nComprimento: %.1f cm\nPlissada: %s",
            saia.getId(),
            saia.getCor(),
            saia.getTecido(),
            saia.getComprimento(),
            saia.isPlissada() ? "Sim" : "Não"
        );
    }

    private boolean camposVazios() {
        return txtCor.getText().isEmpty() ||
               txtTecido.getText().isEmpty() ||
               txtComprimento.getText().isEmpty();
    }

    private void limparCampos() {
        txtCor.clear();
        txtTecido.clear();
        txtComprimento.clear();
        chkPlissada.setSelected(false);
        txtResultado.clear();
        tableSaias.getSelectionModel().clearSelection();
    }

    private void mostrarMensagem(String mensagem) {
        txtResultado.setText(mensagem);
    }
}


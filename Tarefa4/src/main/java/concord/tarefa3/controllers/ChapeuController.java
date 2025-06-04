package concord.tarefa3.controllers;

import concord.tarefa3.dao.ChapeuDAO;
import concord.tarefa3.models.Chapeu;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChapeuController {
    @FXML private TextField txtCor;
    @FXML private TextField txtTamanho;
    @FXML private TextField txtMaterial;
    @FXML private TextField txtMarca;
    @FXML private TextArea txtResultado;
    @FXML private TableView<Chapeu> tableChapeus;
    @FXML private TableColumn<Chapeu, Integer> colId;
    @FXML private TableColumn<Chapeu, String> colCor;
    @FXML private TableColumn<Chapeu, String> colTamanho;
    @FXML private TableColumn<Chapeu, String> colMaterial;
    @FXML private TableColumn<Chapeu, String> colMarca;
    
    private ChapeuDAO chapeuDAO;
    private ObservableList<Chapeu> chapeusList;
    
    @FXML
    public void initialize() {
        chapeuDAO = new ChapeuDAO();
        chapeusList = FXCollections.observableArrayList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        colMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        
        tableChapeus.setItems(chapeusList);
        
        tableChapeus.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                preencherCampos(newSelection);
            }
        });
        
        atualizarLista();
    }
    
    @FXML
    private void criarChapeu() {
        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            Chapeu chapeu = new Chapeu();
            chapeu.setCor(txtCor.getText());
            chapeu.setTamanho(txtTamanho.getText());
            chapeu.setMaterial(txtMaterial.getText());
            chapeu.setMarca(txtMarca.getText());

            chapeuDAO.criar(chapeu);
            mostrarMensagem("Chapéu cadastrado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao criar chapéu: " + e.getMessage());
        }
    }
    
    @FXML
    private void listarChapeus() {
        atualizarLista();
        mostrarMensagem("Lista de chapéus atualizada!");
    }

    @FXML
    private void atualizarChapeu() {
        Chapeu chapeuSelecionado = tableChapeus.getSelectionModel().getSelectedItem();
        if (chapeuSelecionado == null) {
            mostrarMensagem("Por favor, selecione um chapéu para atualizar.");
            return;
        }

        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            chapeuSelecionado.setCor(txtCor.getText());
            chapeuSelecionado.setTamanho(txtTamanho.getText());
            chapeuSelecionado.setMaterial(txtMaterial.getText());
            chapeuSelecionado.setMarca(txtMarca.getText());

            chapeuDAO.atualizar(chapeuSelecionado);
            mostrarMensagem("Chapéu atualizado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao atualizar chapéu: " + e.getMessage());
        }
    }

    @FXML
    private void deletarChapeu() {
        Chapeu chapeuSelecionado = tableChapeus.getSelectionModel().getSelectedItem();
        if (chapeuSelecionado == null) {
            mostrarMensagem("Por favor, selecione um chapéu para deletar.");
            return;
        }

        try {
            chapeuDAO.deletar(chapeuSelecionado.getId());
            mostrarMensagem("Chapéu deletado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao deletar chapéu: " + e.getMessage());
        }
    }

    private void atualizarLista() {
        chapeusList.clear();
        chapeusList.addAll(chapeuDAO.listarTodos());
    }

    private void preencherCampos(Chapeu chapeu) {
        txtCor.setText(chapeu.getCor());
        txtTamanho.setText(chapeu.getTamanho());
        txtMaterial.setText(chapeu.getMaterial());
        txtMarca.setText(chapeu.getMarca());
        txtResultado.setText(formatarChapeu(chapeu));
    }

    private String formatarChapeu(Chapeu chapeu) {
        return String.format("ID: %d\nCor: %s\nTamanho: %s\nMaterial: %s\nMarca: %s",
            chapeu.getId(),
            chapeu.getCor(),
            chapeu.getTamanho(),
            chapeu.getMaterial(),
            chapeu.getMarca()
        );
    }

    private boolean camposVazios() {
        return txtCor.getText().isEmpty() ||
               txtTamanho.getText().isEmpty() ||
               txtMaterial.getText().isEmpty() ||
               txtMarca.getText().isEmpty();
    }

    private void limparCampos() {
        txtCor.clear();
        txtTamanho.clear();
        txtMaterial.clear();
        txtMarca.clear();
        txtResultado.clear();
        tableChapeus.getSelectionModel().clearSelection();
    }

    private void mostrarMensagem(String mensagem) {
        txtResultado.setText(mensagem);
    }
}

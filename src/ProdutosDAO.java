/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
         java.sql.Connection conn;
        java.sql.PreparedStatement prep;
    
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
    
        try {
        conectaDAO conecta = new conectaDAO();
        conn = conecta.connectDB();
        prep = conn.prepareStatement(sql);
        
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        
        prep.execute();
        prep.close();
        conn.close();
        
        } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Erro ao cadastrar no DAO: " + e.getMessage());
        }

        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        java.util.ArrayList<ProdutosDTO> listagem = new java.util.ArrayList<>();
        String sql = "SELECT * FROM produtos";
        
        try {
            conectaDAO conecta = new conectaDAO();
            java.sql.Connection conn = conecta.connectDB();
            java.sql.PreparedStatement prep = conn.prepareStatement(sql);
            java.sql.ResultSet resultset = prep.executeQuery();
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
            
            resultset.close();
            prep.close();
            conn.close();
            
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao listar no DAO: " + e.getMessage());
        }
        return listagem;
    }

       
            // Método para atualizar o status do produto para 'Vendido'
        public void venderProduto(int id) {
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            try {
                conectaDAO conecta = new conectaDAO();
                java.sql.Connection conn = conecta.connectDB();
                java.sql.PreparedStatement prep = conn.prepareStatement(sql);
            
                 prep.setInt(1, id);
                prep.executeUpdate();
            
                prep.close();
                conn.close();
                javax.swing.JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
            }
        }

        // Método para buscar apenas os produtos com status 'Vendido'
        public java.util.ArrayList<ProdutosDTO> listarProdutosVendidos() {
            java.util.ArrayList<ProdutosDTO> listagem = new java.util.ArrayList<>();
            String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
            try {
                conectaDAO conecta = new conectaDAO();
                java.sql.Connection conn = conecta.connectDB();
                java.sql.PreparedStatement prep = conn.prepareStatement(sql);
                java.sql.ResultSet resultset = prep.executeQuery();
            
                while (resultset.next()) {
                    ProdutosDTO produto = new ProdutosDTO();
                    produto.setId(resultset.getInt("id"));
                    produto.setNome(resultset.getString("nome"));
                    produto.setValor(resultset.getInt("valor"));
                    produto.setStatus(resultset.getString("status"));
                    listagem.add(produto);
                }
                resultset.close();
                prep.close();
                conn.close();
            } catch (java.sql.SQLException e) {
                javax.swing.JOptionPane.showMessageDialog(null, "Erro ao listar vendidos: " + e.getMessage());
            }
            return listagem;
        }
}

     



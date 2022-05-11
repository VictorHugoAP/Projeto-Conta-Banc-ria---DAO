package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import model.ContaBancaria;

public class ProdutoDao {
	private Connection conexao;
	
	public ProdutoDao() {
		this.conexao = new ConnectionFactory().getConnection();
	}
	
	public ContaBancaria acessarConta(int numero, int senha) {
		String sql = "SELECT CONTAS.CON_NOME, CONTAS.CON_NUMERO, CONTAS.CON_SALDO, CONTAS.CON_SENHA " +
					"FROM CONTAS " +
					"WHERE CONTAS.CON_NUMERO = ? AND CONTAS.CON_SENHA = ?";
		
		ContaBancaria conta = new ContaBancaria();
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, numero);
			stm.setInt(2, senha);
			
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				conta.setNumero(rs.getInt("CON_NUMERO"));
				conta.setNomeProprietario(rs.getString("CON_NOME"));
				conta.setSaldo(rs.getDouble("CON_SALDO"));		
				conta.setSenha(rs.getInt("CON_SENHA"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return conta;	
	}
	
	public ContaBancaria getContaByNumero(int numero) {
		String sql = "SELECT CONTAS.CON_NUMERO " +
					"FROM CONTAS " +
					"WHERE CONTAS.CON_NUMERO = ?";
		
		ContaBancaria conta = new ContaBancaria();
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, numero);
			
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				conta.setNumero(rs.getInt("CON_NUMERO"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return conta;	
	}
	
	public List<ContaBancaria> getContas() {
		String sql = "SELECT CONTAS.CON_NOME, CONTAS.CON_NUMERO " +
					"FROM CONTAS ";
		
		List<ContaBancaria> listaContas = new ArrayList<ContaBancaria>();	
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
					ContaBancaria conta = new ContaBancaria();
					conta.setNumero(rs.getInt("CON_NUMERO"));
					conta.setNomeProprietario(rs.getString("CON_NOME"));
					listaContas.add(conta);	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return listaContas;	
	}
	
	public void insertConta(ContaBancaria conta) {
		String sql = "INSERT INTO CONTAS(CON_NOME, CON_NUMERO, CON_SALDO, CON_SENHA) " +
					"VALUES(?,?,?,?)";
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setString(1, conta.getNomeProprietario());
			stm.setInt(2, conta.getNumero());
			stm.setDouble(3, conta.getSaldo());
			stm.setInt(4, conta.getSenha());
			
			stm.execute();
			System.out.println("Conta criada com sucesso!\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void deleteConta(int numero, int senha) {
		String sql = "DELETE FROM CONTAS WHERE CONTAS.CON_NUMERO = ? AND CONTAS.CON_SENHA = ? ";
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, numero);
			stm.setInt(2, senha);
			
			System.out.println("\nConta excluída com sucesso!\n");
			stm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void atualizarNome(ContaBancaria conta, String nome) {
		String sql = "UPDATE CONTAS " +
					"SET CON_NOME = ? " +
					"WHERE CONTAS.CON_NUMERO = ? AND CONTAS.CON_SENHA = ?";
		
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setString(1, nome);
			stm.setInt(2, conta.getNumero());
			stm.setInt(3, conta.getSenha());
			
			String nomeAntigo = conta.getNomeProprietario();
			conta.setNomeProprietario(nome);
			stm.execute();	
			System.out.println("\nConta: " + conta.getNumero() + "\nNovo Nome: " + nome + "\nAntigo Nome: " + nomeAntigo + "\n");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualizarNumero(ContaBancaria conta, int numero) {
		String sql = "UPDATE CONTAS " +
					"SET CON_NUMERO = ? " +
					"WHERE CONTAS.CON_NUMERO = ? AND CONTAS.CON_SENHA = ?";
		
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, numero);
			stm.setInt(2, conta.getNumero());
			stm.setInt(3, conta.getSenha());
			
			int numeroAntigo = conta.getNumero();
			conta.setNumero(numero);
			stm.execute();	
			System.out.println("\nNovo número da Conta: " + conta.getNumero() + "\nNúmero antigo: " +  numeroAntigo + "\n");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualizarSenha(ContaBancaria conta, int senha) {
		String sql = "UPDATE CONTAS " +
					"SET CON_SENHA = ? " +
					"WHERE CONTAS.CON_NUMERO = ? AND CONTAS.CON_SENHA = ?";
		
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, senha);
			stm.setInt(2, conta.getNumero());
			stm.setInt(3, conta.getSenha());
			
			int senhaAntiga = conta.getSenha();
			conta.setSenha(senha);
			stm.execute();	
			System.out.println("\nNova senha da Conta: " + conta.getSenha() + "\nSenha antiga: " +  senhaAntiga + "\n");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void depositar(int numero, double valor) {
		String sql = "UPDATE CONTAS " +
				"SET CON_SALDO = ? + CON_SALDO " +
				"WHERE CONTAS.CON_NUMERO = ?";
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setDouble(1, valor);
			stm.setInt(2, numero);
			
			
			stm.execute();
			System.out.println("Depositado R$ " + valor + " na conta " + numero);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void transferir(int contaOrigem, int numeroContaDestino, double valor) {
		String sql = "UPDATE CONTAS " +
				"SET CON_SALDO = CON_SALDO + ? " +
				"WHERE CONTAS.CON_NUMERO = ?";
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setDouble(1, valor);
			stm.setInt(2, numeroContaDestino);
			
			stm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql2 = "UPDATE CONTAS " +
				"SET CON_SALDO = CON_SALDO - ? " +
				"WHERE CONTAS.CON_NUMERO = ?";
		
		try {
			PreparedStatement stm2 = conexao.prepareStatement(sql2);
			stm2.setDouble(1, valor);
			stm2.setInt(2, contaOrigem);
			
			
			stm2.execute();
			System.out.println("\nTransferido R$ " + valor + " da conta(" + contaOrigem + ") para a conta(" + numeroContaDestino + ")\n"); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

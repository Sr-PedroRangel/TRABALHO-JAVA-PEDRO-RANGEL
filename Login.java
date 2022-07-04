package br.modelo.senac.br;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author pedro
 * 
 */


public abstract class Login {
	private int agencia;
	private int numero;
	private String titular;
	protected double saldo;
	public static int totalContas;

	Login() {
		this.titular = "";
		Conta.totalContas++;
	}
/**
 * 
 * @param numAgencia
 * @param numConta
 */
	Login(int numAgencia, int numConta) {
		this();
		this.agencia = numAgencia;
		this.numero = numConta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void deposita(double valor) {
		this.saldo = this.saldo + valor;
	}

	public abstract boolean saca(double valor);

	public boolean cadastrarLogin(int numAgencia, int numConta, String titular) {
		
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			
			String sql = "insert into login set agencia=?, numero=?, titular=?, saldo=0;";
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, numAgencia); 
			ps.setInt(2, numConta); 
			ps.setString(3, titular); 
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar a conta: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean consultarConta(int numAgencia, int numConta) {
		
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
		
			String sql = "select * from conta where agencia=? and numero=?";
		
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, numAgencia); 
			ps.setInt(2, numConta); 
			
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { 
				return false; 
			} else {
				while (rs.next()) {
					this.agencia = rs.getInt("agencia");
					this.numero = rs.getInt("numero");
					this.titular = rs.getString("titular");
					this.saldo = rs.getDouble("saldo");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar a conta: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean atualizarConta(int numAgencia, int numConta, String titular) {
		if (!consultarConta(numAgencia, numConta))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				String sql = "update conta set titular=?, saldo=? where=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, titular);
				ps.setDouble(2, saldo);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar a conta: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
}

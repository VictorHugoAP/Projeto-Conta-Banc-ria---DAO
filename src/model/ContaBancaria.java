package model;

public class ContaBancaria {
	private String nomeProprietario;
	private int numero;
	private int senha;
	private double saldo;
	
	public ContaBancaria() {
		
	}
	
	public ContaBancaria(String nome, int numero, int senha) {
		this.numero = numero;
		this.senha = senha;
		this.nomeProprietario = nome;
		this.saldo = 0.0;
	}
	
	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public void Sacar(double valor) {
		if(this.saldo >= valor) {
			this.saldo -= valor;
			System.out.println("Saque de: R$ " + valor + " Realizado - Saldo Atual: R$ " + this.saldo);
		}else {
			System.out.println("Saldo Insuficiente! O Saldo Atual é de: R$ \n" + this.saldo);
		}
	}
	
	public void Depositar() {
		
	}

	public String toString() {
		String s = "\n----Informações da Conta----\n" +
					"Proprietário: " + this.nomeProprietario + "\n" +
					"Número da Conta: " + this.numero + "\n" +
					"Saldo: " + this.saldo;
		
		if(this.numero < 1) {
			System.out.println("Conta não Encontrada!");
			return "";
		}else {
			return s;		
		}	
	}
	
	
}

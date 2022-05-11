package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ProdutoDao;
import model.ContaBancaria;

public class Menu {
	
	ProdutoDao produtoDao = new ProdutoDao();
	
	public void executar() {
		int opcao;
		String opcoesMenu1 = "----Escolha uma Opção----\n" +
				   "1) Criar nova conta\n" +
				   "2) Acessar Conta\n" +
				   "3) Efetuar um depósito\n" +
				   "4) Mostrar todas as contas cadastradas\n" +
				   "0) Sair\n";
		do {
			opcao = leInteiro(opcoesMenu1);	
			switch(opcao) {
				case 1:
					String nome = leString("Digite seu Nome: ");
					int numero = leInteiro("Escolha um número para a conta: ");
					int senha = leInteiro("Escolha uma senha:");
				
					ContaBancaria verificarConta = produtoDao.getContaByNumero(numero);
					
					if(verificarConta.getNumero() > 0) {
						System.out.println("Conta Já existe!");
					}else {
						produtoDao.insertConta(new ContaBancaria(nome, numero, senha));					
					}			
				
					break;
					
				case 2:
					ContaBancaria conta = produtoDao.acessarConta(leInteiro("Digite o número da sua Conta: "), leInteiro("Digite a sua senha: "));
					if(conta.getSenha() < 1) {
						System.out.println("Conta não encontrada ou senha inválida\n");
						break;
					}else {
						String opcoesMenu2 = "\nEscolha uma Opção\n" +
								"1 - Editar conta\n" +
								"2 - Realizar um transferência\n" +
								"3 - Excluir conta\n" +
								"0 - Voltar ao menu principal\n";
						
						int opcaoC;
						
						do {
							System.out.println(conta);
							opcaoC = leInteiro(opcoesMenu2);
							
							switch(opcaoC) {
								case 1:
									int opcaoD;
									String opcoesMenu3 = "Escolha uma Opção\n" +
														 "1 - Editar nome\n" +
														 "2 - Editar número\n" +
														 "3 - Editar senha\n" +
														 "0 - Voltar ao menu da conta";
									do {
										opcaoD = leInteiro(opcoesMenu3);
										
										switch(opcaoD) {
											case 1:
												produtoDao.atualizarNome(conta, leString("Digite o novo nome: "));
												break;
												
											case 2: 
												produtoDao.atualizarNumero(conta, leInteiro("Digite o novo numero: "));
												break;
												
											case 3:
												produtoDao.atualizarSenha(conta, leInteiro("Digite a nova senha"));
												break;
												
											default:
												opcaoD = 0;
										}
									}while(opcaoD != 0);
									break;
									
								case 2:
									double valor = leDouble("Informe o valor Desejado: ");
									if(conta.getSaldo() >= valor) {
										produtoDao.transferir(conta.getNumero(), leInteiro("Digite o número da conta que deseja transferir"), valor);
										conta.setSaldo(conta.getSaldo() - valor);
									}else {
										System.out.println("Saldo Insuficiente, Saldo atual é de R$ " + conta.getSaldo());
									}
									
									break;
								
								case 3:
									int opcaoE;
									do {
										opcaoE = leInteiro("Tem certeza que deseja excluir essa conta?\n 1 - Sim 2 - Não");
										
										switch(opcaoE) {
											case 1:
												produtoDao.deleteConta(conta.getNumero(), conta.getSenha());
												opcaoC = 0;
												opcaoE = 0;
												break;
											
											case 2:
												System.out.println("Voltando...\n");
												opcaoE = 0;
												break;
												
											default:
												opcaoE = 0;
										}
										
									}while(opcaoE != 0); 
									break;
									
								default:
									opcaoC = 0;
							}				
						}while(opcaoC != 0);
						break;
					}

				case 3:
					produtoDao.depositar(leInteiro("Número da conta destino: "), leDouble("Valor Desejado: "));						
					break;
					
				case 4:
					List<ContaBancaria> listaContas = new ArrayList<ContaBancaria>();
					listaContas = produtoDao.getContas();

					if(listaContas.size() == 0) {
						System.out.println("\nNenhuma conta cadastrada\n");
					}else {
						System.out.println("\nLista de Contas Cadastradas\n");
						
						for(ContaBancaria c : listaContas) {
							System.out.println("Nome Proprietario: " + c.getNomeProprietario() + "\nNúmero: " + c.getNumero() + "\n");
						}
					}
					
					break;
				
				default:
					System.out.println("\nPrograma Encerrado!");
					opcao = 0;
			}		
		}while(opcao != 0);
	}
	
	public int leInteiro(String pergunta) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println(pergunta);
		return sc.nextInt();		
	}
	
	public double leDouble(String pergunta) {
		Scanner sc = new Scanner(System.in);
		System.out.println(pergunta);
		return sc.nextDouble();		
	}
	
	
	public String leString(String pergunta) {
		Scanner sc = new Scanner(System.in);
		System.out.println(pergunta);
		return sc.nextLine();
	}
}

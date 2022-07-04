package br.modelo.senac.br;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

		public class TesteSwing {
			public static void main(String[] args) {
				
				JFrame janela = new JFrame("Login de conta"); 
				janela.setResizable(false); 
				janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				janela.setSize(400, 300); 
				Container caixa = janela.getContentPane();
				caixa.setLayout(null);
				
				JLabel labelAgencia = new JLabel("Email: ");
				JLabel labelNumero = new JLabel("Senha: ");
				JLabel labelTitular = new JLabel("Confirmar Senha: ");
				JLabel labelAgencia = new JLabel("Código de Segurança: ");
				labelAgencia.setBounds(50, 60, 100, 40); 
				labelNumero.setBounds(50, 80, 150, 20); 
				labelTitular.setBounds(50, 120, 100, 20); 
				
				JTextField jTextAgencia = new JTextField();
				JTextField jTextNumero = new JTextField();
				JTextField jTextTitular = new JTextField();
				JTextField jTextAgencia = new JTextField();
				
				jTextAgencia.setEnabled(true);
				jTextNumero.setEnabled(true);
				jTextTitular.setEnabled(false);
				jTextNumero.setEnabled(true);
				
				jTextAgencia.setBounds(180, 40, 50, 20);
				jTextNumero.setBounds(180, 80, 50, 20);
				jTextTitular.setBounds(180, 120, 150, 20);
				jTextAgencia.setBounds(180, 40, 50, 20);
				
				janela.add(labelAgencia);
				janela.add(labelNumero);
				janela.add(labelTitular);
				janela.add(jTextAgencia);
				janela.add(jTextNumero);
				janela.add(jTextTitular);
				// Define botões e a localização deles na janela
				JButton botaoVerficar = new JButton("Verficar");
				botaoVerficar.setBounds30, 80, 100, 20);
				janela.add(botaoVerficar);
				JButton botaoConfirmar = new JButton("Confirmar");
				botaoConfirmar.setBounds(50, 200, 100, 20);
				botaoConfirmar.setEnabled(false);
				janela.add(botaoConfirmar);
				JButton botaoLimpar = new JButton("Limpar");
				botaoLimpar.setBounds(250, 200, 100, 20);
				janela.add(botaoLimpar);
				ContaCorrenteNormal conta = new ContaCorrenteNormal();
				botaoConsultar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int agencia = Integer.parseInt(jTextAgencia.getText());
							int numero = Integer.parseInt(jTextNumero.getText());
							botaoGravar.setEnabled(true);
							String titular;
							if (!conta.consultarConta(agencia, numero))
								titular = "";
							else
								titular = conta.getTitular();
							jTextTitular.setText(titular);
							jTextAgencia.setEnabled(false);
							jTextNumero.setEnabled(false);
							botaoConsultar.setEnabled(false);
							jTextTitular.setEnabled(true);
							jTextTitular.requestFocus();
						} catch (Exception erro) {
							JOptionPane.showMessageDialog(janela,
									"Preencha os campos Login e Senha corretamente!!");
						}
					}
				});
				botaoGravar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int agencia = Integer.parseInt(jTextAgencia.getText());
						int numero = Integer.parseInt(jTextNumero.getText());
						String titular = jTextTitular.getText().trim(); // Retira os espaços em branco
						if (titular.length()==0) {
							JOptionPane.showMessageDialog(janela, "Preencha o campo titular");
							jTextTitular.requestFocus();
						}
						else {
							if (!conta.consultarConta(agencia, numero)) {
								if (!conta.cadastrarConta(agencia, numero, titular))
									JOptionPane.showMessageDialog(janela, "Erro na inclusão do titular!");
								else
									JOptionPane.showMessageDialog(janela, "Inclusão realizada!");
							} else {
								if (!conta.atualizarConta(agencia, numero, titular))
									JOptionPane.showMessageDialog(janela, "Erro na atualização do titular!");
								else
									JOptionPane.showMessageDialog(janela, "Alteração realizada!");
							}

						}
					}
				});
				botaoLimpar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jTextAgencia.setText(""); 
						jTextNumero.setText(""); 
						jTextTitular.setText(""); 
						jTextAgencia.setEnabled(true);
						jTextNumero.setEnabled(true);
						jTextTitular.setEnabled(false);
						botaoConsultar.setEnabled(true);
						botaoGravar.setEnabled(false);
						jTextAgencia.requestFocus();
					}
				});
				
				janela.setVisible(true); 
			}
			{
	}

}

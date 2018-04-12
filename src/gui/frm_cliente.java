package gui;

// Importando bibliotecas
import java.sql.*; // SQL
import database.Banco; // Classe Banco de Dados
import javax.swing.JOptionPane; // Janelas de Mensagens

public class frm_cliente extends javax.swing.JFrame {

    // Instancia da classe de Conexão
    Banco banco;

    // controlar erro de navegacao nos botoes proximo e anterior
    int navega = 0;

    // select principal
    String sql = "select * from cliente";
    
    // Construtor
    public frm_cliente() {
        initComponents();
        
        // Criando objeto da classe Banco
        banco = new Banco();

        // Abrir conexão com o banco de dados
        banco.connect();

        // Carregar dados
        banco.executeSQL(sql);
        
        try {
            // Procura o primeiro registro no banco
            banco.resultset.first();
            exibir_dados();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar dados\n" + e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    // Método exibir_dados - mostra os dados na tela
    public void exibir_dados() {
        try {          
            lbl_id_cli.setText(banco.resultset.getString(1)); // Código
            txt_nome_cli.setText(banco.resultset.getString(2)); // Nome
            txt_cpf_cli.setText(banco.resultset.getString(3)); // CPF
            txt_dtnasc_cli.setText(banco.resultset.getString(4)); // Data Nascimento
            txt_telefone_cli.setText(banco.resultset.getString(5)); // Telefone
            txt_endereco_cli.setText(banco.resultset.getString(6)); // Endereço
            txt_estado_civil_cli.setText(banco.resultset.getString(7)); // Estado Civil
            lbl_pontos.setText(banco.resultset.getString(8)); // Pontos
            
        } catch (SQLException e) {
            if (navega == 1) {
                JOptionPane.showMessageDialog(null, "Erro!\nVocê já está no primeiro registro.\n" + e, "Erro!", JOptionPane.ERROR_MESSAGE);
            } else if (navega == 2) {
                JOptionPane.showMessageDialog(null, "Erro!\nVocê já está no último registro.\n" + e, "Erro!", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao acessar dados\n" + e, "Erro!", JOptionPane.ERROR_MESSAGE);
                navega = 0;
            }
        }
    }
    
    // Método salvar - cria um novo registro
    /*public void salvar() {
        try {
            // Guardar informações da tela em variáveis
            String tipo = txt_tipo.getText();
            String nome = txt_nome.getText();
            String cpf = txt_cpf.getText();
            String endereco = txt_endereco.getText();
            String data_nascto = txt_data_nascto.getText();
            String funcao = txt_funcao.getText();
            String telefone = txt_telefone.getText();
            String estado_civil = txt_estado_civil.getText();
            double salario = Double.parseDouble(txt_salario.getText());

            // Comando SQL
            String comando = "insert into funcionario (id_tipo, nome_fun, cpf_fun, endereco_fun, data_nascto_fun, funcao_fun, telefone_fun, estado_civil_fun, salario_fun) values "
                    + "('" + tipo + "', '" + nome + "', '" + cpf + "', '" + endereco + "', '" + data_nascto + "', '" + funcao + "', '" + telefone + "', '" + estado_civil + "', " + salario + ")";

            // Executar comando SQL
            banco.statement.executeUpdate(comando);
            JOptionPane.showMessageDialog(null, "Informações salvas com sucesso.", "Pronto", JOptionPane.OK_OPTION);

            // Mostra o primeiro registro novamente  
            banco.executeSQL(sql);
            banco.resultset.first();
            exibir_dados();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar informações!\n" + e, "Erro!", JOptionPane.ERROR_MESSAGE);
            System.out.println("erro" +e);
        }
    }*/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_codigo = new javax.swing.JLabel();
        lbl_id = new javax.swing.JLabel();
        lbl_nome = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        lbl_data_nascto = new javax.swing.JLabel();
        txt_data_nascto = new javax.swing.JTextField();
        lbl_endereco = new javax.swing.JLabel();
        txt_endereco = new javax.swing.JTextField();
        txt_cpf = new javax.swing.JFormattedTextField();
        lbl_cpf = new javax.swing.JLabel();
        lbl_estado_civil = new javax.swing.JLabel();
        txt_estado_civil = new javax.swing.JTextField();
        lbl_telefone = new javax.swing.JLabel();
        txt_telefone = new javax.swing.JTextField();
        pnl_clientes = new javax.swing.JPanel();
        lbl_codigo_cli = new javax.swing.JLabel();
        lbl_nome_cli = new javax.swing.JLabel();
        lbl_dtnasc_cli = new javax.swing.JLabel();
        lbl_estado_civil_cli = new javax.swing.JLabel();
        lbl_cpf_cli = new javax.swing.JLabel();
        lbl_endereco_cli = new javax.swing.JLabel();
        lbl_telefone_cli = new javax.swing.JLabel();
        lbl_email_cli = new javax.swing.JLabel();
        txt_nome_cli = new javax.swing.JTextField();
        txt_dtnasc_cli = new javax.swing.JTextField();
        txt_cpf_cli = new javax.swing.JTextField();
        txt_estado_civil_cli = new javax.swing.JTextField();
        lbl_id_cli = new javax.swing.JLabel();
        txt_endereco_cli = new javax.swing.JTextField();
        txt_telefone_cli = new javax.swing.JTextField();
        txt_email_cli = new javax.swing.JTextField();
        btn_anterior = new javax.swing.JButton();
        btn_proximo = new javax.swing.JButton();
        btn_alterar = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_novo = new javax.swing.JButton();
        btn_voltar = new javax.swing.JButton();
        lbl_anterior = new javax.swing.JLabel();
        lbl_proximo = new javax.swing.JLabel();
        lbl_alterar = new javax.swing.JLabel();
        lbl_excluir = new javax.swing.JLabel();
        lbl_salvar = new javax.swing.JLabel();
        lbl_voltar = new javax.swing.JLabel();
        lbl_novo = new javax.swing.JLabel();
        lbl_cancelar = new javax.swing.JLabel();
        lbl_pontuacao = new javax.swing.JLabel();
        lbl_pontos = new javax.swing.JLabel();

        lbl_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_codigo.setText("Código");

        lbl_id.setText("Código do Funcionário");

        lbl_nome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_nome.setText("Nome");

        lbl_data_nascto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_data_nascto.setText("Data Nascimento");

        lbl_endereco.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_endereco.setText("Endereço");

        lbl_cpf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_cpf.setText("CPF");

        lbl_estado_civil.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_estado_civil.setText("Estado Civil");

        lbl_telefone.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_telefone.setText("Telefone");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnl_clientes.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Clientes"));

        lbl_codigo_cli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_codigo_cli.setText("Código");

        lbl_nome_cli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_nome_cli.setText("Nome");

        lbl_dtnasc_cli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_dtnasc_cli.setText("Data de Nascimento");

        lbl_estado_civil_cli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_estado_civil_cli.setText("Estado Civil");

        lbl_cpf_cli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_cpf_cli.setText("CPF");

        lbl_endereco_cli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_endereco_cli.setText("Endereço");

        lbl_telefone_cli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_telefone_cli.setText("Telefone");

        lbl_email_cli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_email_cli.setText("E-mail");

        txt_nome_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nome_cliActionPerformed(evt);
            }
        });

        lbl_id_cli.setText("Código do Cliente");

        txt_endereco_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_endereco_cliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_clientesLayout = new javax.swing.GroupLayout(pnl_clientes);
        pnl_clientes.setLayout(pnl_clientesLayout);
        pnl_clientesLayout.setHorizontalGroup(
            pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_clientesLayout.createSequentialGroup()
                .addGroup(pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_email_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnl_clientesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txt_dtnasc_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_cpf_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_estado_civil_cli)
                                    .addComponent(txt_estado_civil_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_telefone_cli)))
                            .addGroup(pnl_clientesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnl_clientesLayout.createSequentialGroup()
                                        .addComponent(lbl_codigo_cli)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbl_id_cli))
                                    .addComponent(lbl_nome_cli)
                                    .addGroup(pnl_clientesLayout.createSequentialGroup()
                                        .addComponent(lbl_dtnasc_cli)
                                        .addGap(43, 43, 43)
                                        .addComponent(lbl_cpf_cli))
                                    .addComponent(txt_nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnl_clientesLayout.createSequentialGroup()
                                        .addComponent(txt_endereco_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_telefone_cli)))))
                        .addGroup(pnl_clientesLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbl_endereco_cli)
                                .addComponent(lbl_email_cli)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pnl_clientesLayout.setVerticalGroup(
            pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_clientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_codigo_cli)
                    .addComponent(lbl_id_cli))
                .addGap(18, 18, 18)
                .addComponent(lbl_nome_cli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_dtnasc_cli)
                    .addComponent(lbl_estado_civil_cli)
                    .addComponent(lbl_cpf_cli))
                .addGap(5, 5, 5)
                .addGroup(pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_dtnasc_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_estado_civil_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cpf_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_endereco_cli)
                    .addComponent(lbl_telefone_cli))
                .addGap(3, 3, 3)
                .addGroup(pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_endereco_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_telefone_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_email_cli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_email_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        btn_anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Anterior.png"))); // NOI18N

        btn_proximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Proximo.png"))); // NOI18N

        btn_alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Alterar.png"))); // NOI18N

        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar.png"))); // NOI18N

        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Cancelar.png"))); // NOI18N

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Novo.png"))); // NOI18N

        btn_voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Voltar.png"))); // NOI18N

        lbl_anterior.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_anterior.setText("Anterior");

        lbl_proximo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_proximo.setText("Próximo");

        lbl_alterar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_alterar.setText("Alterar");

        lbl_excluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_excluir.setText("Excluir");

        lbl_salvar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_salvar.setText("Salvar");

        lbl_voltar.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lbl_voltar.setText("Voltar");

        lbl_novo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_novo.setText("Novo");

        lbl_cancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_cancelar.setText("Cancelar");

        lbl_pontuacao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_pontuacao.setText("Pontuação");

        lbl_pontos.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbl_pontos.setForeground(new java.awt.Color(0, 204, 0));
        lbl_pontos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_pontos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnl_clientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl_anterior)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbl_proximo)))
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lbl_pontuacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lbl_alterar)
                                        .addGap(20, 20, 20)
                                        .addComponent(lbl_excluir)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbl_salvar)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_cancelar)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lbl_pontos, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lbl_voltar)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(lbl_novo))
                                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(24, 24, 24))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_pontos, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnl_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(lbl_novo))
                                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btn_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_anterior)
                                        .addComponent(lbl_proximo))))
                            .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_alterar)
                                    .addComponent(lbl_excluir)
                                    .addComponent(lbl_salvar)))
                            .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_cancelar)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_voltar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lbl_pontuacao)))))
                .addGap(0, 32, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nome_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nome_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nome_cliActionPerformed

    private void txt_endereco_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_endereco_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_endereco_cliActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_alterar;
    private javax.swing.JButton btn_anterior;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_proximo;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JButton btn_voltar;
    private javax.swing.JLabel lbl_alterar;
    private javax.swing.JLabel lbl_anterior;
    private javax.swing.JLabel lbl_cancelar;
    private javax.swing.JLabel lbl_codigo;
    private javax.swing.JLabel lbl_codigo_cli;
    private javax.swing.JLabel lbl_cpf;
    private javax.swing.JLabel lbl_cpf_cli;
    private javax.swing.JLabel lbl_data_nascto;
    private javax.swing.JLabel lbl_dtnasc_cli;
    private javax.swing.JLabel lbl_email_cli;
    private javax.swing.JLabel lbl_endereco;
    private javax.swing.JLabel lbl_endereco_cli;
    private javax.swing.JLabel lbl_estado_civil;
    private javax.swing.JLabel lbl_estado_civil_cli;
    private javax.swing.JLabel lbl_excluir;
    private javax.swing.JLabel lbl_id;
    private javax.swing.JLabel lbl_id_cli;
    private javax.swing.JLabel lbl_nome;
    private javax.swing.JLabel lbl_nome_cli;
    private javax.swing.JLabel lbl_novo;
    private javax.swing.JLabel lbl_pontos;
    private javax.swing.JLabel lbl_pontuacao;
    private javax.swing.JLabel lbl_proximo;
    private javax.swing.JLabel lbl_salvar;
    private javax.swing.JLabel lbl_telefone;
    private javax.swing.JLabel lbl_telefone_cli;
    private javax.swing.JLabel lbl_voltar;
    private javax.swing.JPanel pnl_clientes;
    private javax.swing.JFormattedTextField txt_cpf;
    private javax.swing.JTextField txt_cpf_cli;
    private javax.swing.JTextField txt_data_nascto;
    private javax.swing.JTextField txt_dtnasc_cli;
    private javax.swing.JTextField txt_email_cli;
    private javax.swing.JTextField txt_endereco;
    private javax.swing.JTextField txt_endereco_cli;
    private javax.swing.JTextField txt_estado_civil;
    private javax.swing.JTextField txt_estado_civil_cli;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JTextField txt_nome_cli;
    private javax.swing.JTextField txt_telefone;
    private javax.swing.JTextField txt_telefone_cli;
    // End of variables declaration//GEN-END:variables
}

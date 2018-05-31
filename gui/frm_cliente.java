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
        
        btn_salvar.setEnabled(false);
        btn_cancelar.setEnabled(false);
        
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
    public void salvar() {
        try {
            btn_alterar.setEnabled(true);
            btn_anterior.setEnabled(true);
            btn_proximo.setEnabled(true);
            btn_excluir.setEnabled(true);
            
            // Guardar informações da tela em variáveis
            String nome = txt_nome_cli.getText();
            String cpf = txt_cpf_cli.getText();
            String data = txt_dtnasc_cli.getText();
            String telefone = txt_telefone_cli.getText();
            String endereco = txt_endereco_cli.getText();
            String estado_civil = txt_estado_civil_cli.getText();
            int ponto = 0 ; // Um novo cliente começa com ZERO pontos de desconto

            // Comando SQL
            /*
            insert into cliente (nome_cli, cpf_cli, data_nascto_cli, telefone_cli, endereco_cli, estado_civil_cli, ponto_cli) values ('João Silva', '123.456.789-01', '1983-08-29', '19-3741-7000', 'Rua Pastor Hugo Gegembauer, 444', 'Casado', 10);
            */
            String comando = "insert into cliente (nome_cli, cpf_cli, data_nascto_cli, telefone_cli, endereco_cli, estado_civil_cli, ponto_cli) values "
                    + "('" + nome + "', '" + cpf + "', '" + data + "', '" + telefone + "', '" + endereco + "', '" + estado_civil + "', " + ponto + ")";

            // Executar comando SQL
            banco.statement.executeUpdate(comando);
            JOptionPane.showMessageDialog(null, "Informações salvas com sucesso.", "Pronto", JOptionPane.OK_OPTION);

            // Mostra o primeiro registro novamente  
            banco.executeSQL(sql);
            banco.resultset.first();
            exibir_dados();
            
            btn_salvar.setEnabled(false);
            btn_cancelar.setEnabled(false);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar informações!\n" + e, "Erro!", JOptionPane.ERROR_MESSAGE);
            System.out.println("erro" +e);
        }
    }
    
        // Método excluir - exclui um registro
    public void excluir() {
        try {
            // Busca no banco o registro atual
            banco.executeSQL("select * from cliente where codigo_cli = " + lbl_id_cli.getText());
            banco.resultset.first();

            // Mensagem ao usuário para confirma a exclusão
            String mensagem = "Tem certeza que deseja excluir o cliente?\n" + banco.resultset.getString(2) + "\nCPF: " + banco.resultset.getString(3);

            // Verifica se o usuário clicou no SIM e deleta o cliente, se não, faz NADA
            if (JOptionPane.showConfirmDialog(null, mensagem, "Excluir cliente?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                // Exclui o funcionário e se der certo, exibe uma mensagem ao usuário
                if (banco.statement.executeUpdate("delete from cliente where codigo_cli = " + lbl_id_cli.getText()) == 1) {
                    JOptionPane.showMessageDialog(null, "O cliente foi excluído com sucesso.");

                    // Mostra o primeiro registro novamente                        
                    banco.executeSQL(sql);
                    banco.resultset.first();
                    exibir_dados();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir registro!\n" + e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Método alterar - altera um registro
    public void alterar() {
        try{
            String id = lbl_id_cli.getText();
            String nome = txt_nome_cli.getText();
            String cpf = txt_cpf_cli.getText();
            String data = txt_dtnasc_cli.getText();
            String telefone = txt_telefone_cli.getText();
            String endereco = txt_endereco_cli.getText();
            String estado_civil = txt_estado_civil_cli.getText();
            //Comando SQL
            String comando = "update cliente "
                    + " set nome_cli = '"+nome+"',"
                    + " cpf_cli = '"+cpf+"', "
                    + " data_nascto_cli = '"+data+"',"
                    + " telefone_cli = '"+telefone+"',"
                    + " endereco_cli = '"+endereco+"',"                   
                    + " estado_civil_cli ='"+estado_civil+"'"
                    + " where codigo_cli = "+id;
            
           // Executar comando SQL
            banco.statement.executeUpdate(comando);
            JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso\n" , "Pronto", JOptionPane.OK_OPTION);
            
            // Mostra o primeiro registro novamente  
            banco.executeSQL(sql);
            banco.resultset.first();
            exibir_dados();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao alterar informações!\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
        //Método listar_prox - exibe o próximo registro
    public void listar_prox() {
        try {     
            banco.resultset.next();
            exibir_dados();
            navega = 2;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao mostrar informações!\n" + e, "Erro!", JOptionPane.ERROR_MESSAGE);
        } 
    } 
    
    // Método listar_ant - exibe o registro anterior
    public void listar_ant(){
        try {     
            banco.resultset.previous();
            exibir_dados();
            navega = 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao mostrar informações!\n" + e, "Erro!", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    // Método voltar_menu - volta ao menu principal
    public void voltar_menu(){
        // Esse botão volta ao form Principal
        new frm_principal().setVisible(true);
        this.dispose();
        
        // Fechar a conexão com o banco
        banco.disconnect();
    }

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
        txt_nome_cli = new javax.swing.JTextField();
        txt_dtnasc_cli = new javax.swing.JTextField();
        txt_cpf_cli = new javax.swing.JTextField();
        txt_estado_civil_cli = new javax.swing.JTextField();
        lbl_id_cli = new javax.swing.JLabel();
        txt_endereco_cli = new javax.swing.JTextField();
        txt_telefone_cli = new javax.swing.JTextField();
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
        setTitle("Cadastro de Cliente");

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
                .addContainerGap()
                .addGroup(pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnl_clientesLayout.createSequentialGroup()
                            .addComponent(txt_dtnasc_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_cpf_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnl_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbl_estado_civil_cli)
                                .addComponent(txt_estado_civil_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_telefone_cli)))
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
                            .addComponent(txt_telefone_cli)))
                    .addComponent(lbl_endereco_cli))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(70, 70, 70))
        );

        btn_anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Anterior.png"))); // NOI18N
        btn_anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anteriorActionPerformed(evt);
            }
        });

        btn_proximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Proximo.png"))); // NOI18N
        btn_proximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_proximoActionPerformed(evt);
            }
        });

        btn_alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Alterar.png"))); // NOI18N
        btn_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterarActionPerformed(evt);
            }
        });

        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar.png"))); // NOI18N
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Cancelar.png"))); // NOI18N
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Novo.png"))); // NOI18N
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Voltar.png"))); // NOI18N
        btn_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voltarActionPerformed(evt);
            }
        });

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
                    .addComponent(pnl_clientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addComponent(lbl_voltar))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lbl_novo))
                                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btn_voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_pontos, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
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
                .addGap(0, 11, Short.MAX_VALUE))
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

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        //Habilitando botões
        btn_cancelar.setEnabled(true);
        btn_salvar.setEnabled(true);
        
        //Desabilitando botões
        btn_anterior.setEnabled(false);
        btn_proximo.setEnabled(false);
        btn_alterar.setEnabled(false);
        btn_excluir.setEnabled(false);
        
        //Limpando o texto
        txt_cpf_cli.setText(null);
        txt_dtnasc_cli.setText(null);
        txt_endereco_cli.setText(null);
        txt_estado_civil_cli.setText(null);
        txt_nome_cli.setText(null);
        txt_telefone_cli.setText(null);
        lbl_id_cli.setText(null);
        lbl_pontos.setText(null);
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voltarActionPerformed
        voltar_menu();
    }//GEN-LAST:event_btn_voltarActionPerformed

    private void btn_anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anteriorActionPerformed
        listar_ant();
    }//GEN-LAST:event_btn_anteriorActionPerformed

    private void btn_proximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_proximoActionPerformed
        listar_prox();
    }//GEN-LAST:event_btn_proximoActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        excluir();
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterarActionPerformed
        alterar();
    }//GEN-LAST:event_btn_alterarActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        salvar();
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        //Habilitando botões
        btn_anterior.setEnabled(true);
        btn_proximo.setEnabled(true);
        btn_alterar.setEnabled(true);
        btn_excluir.setEnabled(true);
        
        //Desabilitando botões
        btn_cancelar.setEnabled(false);
        btn_salvar.setEnabled(false);
        
        exibir_dados();
    }//GEN-LAST:event_btn_cancelarActionPerformed

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

package gui;

// Importando bibliotecas
import java.sql.*; // SQL
import database.Banco; // Classe Banco de Dados
import javax.swing.JOptionPane; // Janelas de Mensagens

public class frm_funcionario extends javax.swing.JFrame {

    // Instancia da classe de Conexão
    Banco banco;

    // controlar erro de navegacao nos botoes proximo e anterior
    int navega = 0;

    // select principal
    String sql = "select * from funcionario";

    // Construtor
    public frm_funcionario() {
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
            lbl_id.setText(banco.resultset.getString(1)); // Código
            txt_tipo.setText(banco.resultset.getString(2)); // Tipo Funcionário
            txt_nome.setText(banco.resultset.getString(3)); // Nome
            txt_cpf.setText(banco.resultset.getString(4)); // CPF
            txt_endereco.setText(banco.resultset.getString(5)); // Endereço
            txt_data_nascto.setText(banco.resultset.getString(6)); // Data Nascimento
            txt_funcao.setText(banco.resultset.getString(7)); // Função
            txt_telefone.setText(banco.resultset.getString(8)); // Telefone
            txt_estado_civil.setText(banco.resultset.getString(9)); // Estado Civil
            txt_salario.setText(banco.resultset.getString(10)); // Salário
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
    }

    // Método excluir - exclui um registro
    public void excluir() {
        try {
            // Busca no banco o registro atual
            banco.executeSQL("select * from funcionario where id_fun = " + lbl_id.getText());
            banco.resultset.first();

            // Mensagem ao usuário para confirma a exclusão
            String mensagem = "Tem certeza que deseja excluir o funcionário?\n" + banco.resultset.getString(3) + "\nCPF: " + banco.resultset.getString(4);

            // Verifica se o usuário clicou no SIM e deleta o funcionário, se não, faz NADA
            if (JOptionPane.showConfirmDialog(null, mensagem, "Excluir funcionário?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                // Exclui o funcionário e se der certo, exibe uma mensagem ao usuário
                if (banco.statement.executeUpdate("delete from funcionario where id_fun = " + lbl_id.getText()) == 1) {
                    JOptionPane.showMessageDialog(null, "O funcionário foi excluído com sucesso.");

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
            String id = lbl_id.getText();
            String tipo = txt_tipo.getText();
            String nome = txt_nome.getText();
            String cpf = txt_cpf.getText();
            String endereco = txt_endereco.getText();
            String data_nascto = txt_data_nascto.getText();
            String funcao = txt_funcao.getText();
            String telefone = txt_telefone.getText();
            String estado_civil = txt_estado_civil.getText();
            String salario = txt_salario.getText();
            
            //Comando SQL
            String comando = "update funcionario "
                    + " set nome_fun = '"+nome+"',"
                    + " tipo_fun = '"+tipo+"', "
                    + " cpf_fun = "+cpf+","
                    + " endereco_fun = '"+endereco+"',"
                    + " data_nascto_fun = '"+data_nascto+"',"
                    + " funcao_fun = '"+funcao+"',"
                    + " telefone_fun ="+telefone+","
                    + " estado_civil_fun ='"+estado_civil+"',"
                    + " salario_fun= "+salario+""
                    + " where id_fun ="+id;
            
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

        lbl_salario = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        txt_endereco = new javax.swing.JTextField();
        txt_data_nascto = new javax.swing.JTextField();
        txt_funcao = new javax.swing.JTextField();
        lbl_codigo = new javax.swing.JLabel();
        txt_telefone = new javax.swing.JTextField();
        txt_estado_civil = new javax.swing.JTextField();
        txt_salario = new javax.swing.JTextField();
        btn_salvar = new javax.swing.JButton();
        lbl_titulo = new javax.swing.JLabel();
        lbl_nome = new javax.swing.JLabel();
        lbl_cpf = new javax.swing.JLabel();
        lbl_endereco = new javax.swing.JLabel();
        lbl_data_nascto = new javax.swing.JLabel();
        lbl_funcao = new javax.swing.JLabel();
        lbl_telefone = new javax.swing.JLabel();
        lbl_tipo = new javax.swing.JLabel();
        txt_tipo = new javax.swing.JTextField();
        lbl_estado_civil = new javax.swing.JLabel();
        btn_excluir = new javax.swing.JButton();
        btn_anterior = new javax.swing.JButton();
        btn_proximo = new javax.swing.JButton();
        btn_alterar = new javax.swing.JButton();
        lbl_alterar = new javax.swing.JLabel();
        lbl_excluir = new javax.swing.JLabel();
        lbl_salvar = new javax.swing.JLabel();
        lbl_anterior = new javax.swing.JLabel();
        lbl_proximo = new javax.swing.JLabel();
        btn_voltar = new javax.swing.JButton();
        lbl_id = new javax.swing.JLabel();
        lbl_voltar = new javax.swing.JLabel();
        txt_cpf = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Funcionários");
        setBackground(new java.awt.Color(51, 153, 255));
        setResizable(false);

        lbl_salario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_salario.setText("Salário R$");

        lbl_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_codigo.setText("Código");

        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        lbl_titulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_titulo.setText("Cadastro de Funcionários");

        lbl_nome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_nome.setText("Nome");

        lbl_cpf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_cpf.setText("CPF");

        lbl_endereco.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_endereco.setText("Endereço");

        lbl_data_nascto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_data_nascto.setText("Data Nascimento");

        lbl_funcao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_funcao.setText("Função");

        lbl_telefone.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_telefone.setText("Telefone");

        lbl_tipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_tipo.setText("Tipo");

        txt_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tipoActionPerformed(evt);
            }
        });

        lbl_estado_civil.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_estado_civil.setText("Estado Civil");

        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/EXCLUIR.png"))); // NOI18N
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        btn_anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/SETAANTERIOR.png"))); // NOI18N
        btn_anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anteriorActionPerformed(evt);
            }
        });

        btn_proximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/SETAPROXIMO.png"))); // NOI18N
        btn_proximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_proximoActionPerformed(evt);
            }
        });

        btn_alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ALTERAR.png"))); // NOI18N
        btn_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterarActionPerformed(evt);
            }
        });

        lbl_alterar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_alterar.setText("Alterar");

        lbl_excluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_excluir.setText("Excluir");

        lbl_salvar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_salvar.setText("Salvar");

        lbl_anterior.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_anterior.setText("Anterior");

        lbl_proximo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_proximo.setText("Próximo");

        btn_voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair.png"))); // NOI18N
        btn_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voltarActionPerformed(evt);
            }
        });

        lbl_id.setText("Código do Funcionário");

        lbl_voltar.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lbl_voltar.setText("Voltar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(lbl_titulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbl_nome)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbl_codigo)
                                    .addGap(39, 39, 39)
                                    .addComponent(lbl_id))
                                .addComponent(lbl_salario)
                                .addComponent(lbl_endereco)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbl_cpf)
                                        .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbl_data_nascto)
                                        .addComponent(txt_data_nascto, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(30, 30, 30)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbl_telefone)
                                        .addComponent(txt_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(txt_nome)
                                .addComponent(txt_endereco)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(btn_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(4, 4, 4)
                                            .addComponent(lbl_anterior)
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btn_proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lbl_proximo))))
                                    .addGap(35, 35, 35)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btn_alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_alterar))
                                    .addGap(35, 35, 35)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbl_excluir)
                                        .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(36, 36, 36)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_salvar))
                                    .addGap(51, 51, 51)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lbl_voltar)
                                        .addComponent(btn_voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lbl_estado_civil)
                                            .addGap(74, 74, 74)
                                            .addComponent(lbl_funcao))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txt_estado_civil, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txt_funcao, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(26, 26, 26)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_tipo))))
                            .addComponent(txt_salario, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl_titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_codigo)
                    .addComponent(lbl_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_nome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_cpf)
                    .addComponent(lbl_data_nascto)
                    .addComponent(lbl_telefone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_data_nascto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbl_endereco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_estado_civil)
                    .addComponent(lbl_funcao)
                    .addComponent(lbl_tipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_estado_civil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_funcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbl_salario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_salario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_proximo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_anterior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_alterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_salvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_excluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_voltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_alterar)
                            .addComponent(lbl_excluir)
                            .addComponent(lbl_salvar)
                            .addComponent(lbl_voltar)
                            .addComponent(lbl_proximo)
                            .addComponent(lbl_anterior))))
                .addGap(32, 32, 32))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        salvar();
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void txt_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tipoActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        excluir();
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anteriorActionPerformed
        listar_ant();
    }//GEN-LAST:event_btn_anteriorActionPerformed

    private void btn_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterarActionPerformed
        alterar();
    }//GEN-LAST:event_btn_alterarActionPerformed

    private void btn_proximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_proximoActionPerformed
        listar_prox();
    }//GEN-LAST:event_btn_proximoActionPerformed

    private void btn_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voltarActionPerformed
        voltar_menu();
    }//GEN-LAST:event_btn_voltarActionPerformed

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
            java.util.logging.Logger.getLogger(frm_funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new frm_funcionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_alterar;
    private javax.swing.JButton btn_anterior;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_proximo;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JButton btn_voltar;
    private javax.swing.JLabel lbl_alterar;
    private javax.swing.JLabel lbl_anterior;
    private javax.swing.JLabel lbl_codigo;
    private javax.swing.JLabel lbl_cpf;
    private javax.swing.JLabel lbl_data_nascto;
    private javax.swing.JLabel lbl_endereco;
    private javax.swing.JLabel lbl_estado_civil;
    private javax.swing.JLabel lbl_excluir;
    private javax.swing.JLabel lbl_funcao;
    private javax.swing.JLabel lbl_id;
    private javax.swing.JLabel lbl_nome;
    private javax.swing.JLabel lbl_proximo;
    private javax.swing.JLabel lbl_salario;
    private javax.swing.JLabel lbl_salvar;
    private javax.swing.JLabel lbl_telefone;
    private javax.swing.JLabel lbl_tipo;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JLabel lbl_voltar;
    private javax.swing.JFormattedTextField txt_cpf;
    private javax.swing.JTextField txt_data_nascto;
    private javax.swing.JTextField txt_endereco;
    private javax.swing.JTextField txt_estado_civil;
    private javax.swing.JTextField txt_funcao;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JTextField txt_salario;
    private javax.swing.JTextField txt_telefone;
    private javax.swing.JTextField txt_tipo;
    // End of variables declaration//GEN-END:variables
}

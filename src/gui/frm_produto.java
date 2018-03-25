package gui;

// Importando bibliotecas
import java.sql.*; // SQL
import database.Banco; // Classe Banco de Dados
import javax.swing.JOptionPane; // Janelas de Mensagens

public class frm_produto extends javax.swing.JFrame {

    // Instancia da classe de Conexão
    Banco banco;

    // controlar erro de navegacao nos botoes proximo e anterior
    int navega = 0;

    // select principal
    String sql = "select * from produto";

    // Construtor
    public frm_produto() {
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
            lbl_codigo.setText(banco.resultset.getString(1)); // Código
            txt_nome.setText(banco.resultset.getString(2)); // Nome
            txt_descricao.setText(banco.resultset.getString(3)); // Descrição
            txt_quantidade.setText(banco.resultset.getString(4)); // Quantidade
            txt_unidade.setText(banco.resultset.getString(5)); // Unidade
            txt_fornecedor.setText(banco.resultset.getString(6)); // Fornecedor
            txt_ponto.setText(banco.resultset.getString(7)); // Ponto
            txt_categoria.setText(banco.resultset.getString(8)); // Categoria
            txt_preco.setText(banco.resultset.getString(9)); // Preço
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
            String nome = txt_nome.getText();
            String descricao = txt_descricao.getText();
            double quantidade = Double.parseDouble(txt_quantidade.getText());
            String unidade = txt_unidade.getText();
            String fornecedor = txt_fornecedor.getText();
            int ponto = Integer.parseInt(txt_ponto.getText());
            String categoria = txt_categoria.getText();
            double preco = Double.parseDouble(txt_preco.getText());

            // Comando SQL
            String comando = "insert into produto (nome_pro, descricao_pro, quantidade_pro, unidade_pro, fornecedor_pro, ponto_pro, categoria_pro, preco_pro) values "
            + "('" + nome + "', '" + descricao + "', " + quantidade + ", '" + unidade + "', '" + fornecedor + "', " + ponto + ", '" + categoria + "', " + preco + ")";

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
            banco.executeSQL("select * from produto where codigo_pro = " + lbl_codigo.getText());
            banco.resultset.first();

            // Mensagem ao usuário para confirma a exclusão
            String mensagem = "Tem certeza que deseja excluir o produto?\n" + banco.resultset.getString(3) + "\nCódigo: " + banco.resultset.getString(1);

            // Verifica se o usuário clicou no SIM e deleta o produto, se não, faz NADA
            if (JOptionPane.showConfirmDialog(null, mensagem, "Excluir produto?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                // Exclui o funcionário e se der certo, exibe uma mensagem ao usuário
                if (banco.statement.executeUpdate("delete from produto where codigo_pro = " + lbl_codigo.getText()) == 1) {
                    JOptionPane.showMessageDialog(null, "O produto foi excluído com sucesso.");

                    // Mostra o primeiro registro novamente                        
                    banco.executeSQL(sql);
                    banco.resultset.first();
                    exibir_dados();
                }
            }
        } catch (SQLException e) {
            // Se algo der errado, mostre uma mensagem
            JOptionPane.showMessageDialog(null, "Erro ao excluir registro!\n" + e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Método alterar - altera um registro
    public void alterar() {
        try{
            // Guardar informações da tela em variáveis
            int codigo = Integer.parseInt(lbl_codigo.getText());
            String nome = txt_nome.getText();
            String descricao = txt_descricao.getText();
            double quantidade = Double.parseDouble(txt_quantidade.getText());
            String unidade = txt_unidade.getText();
            String fornecedor = txt_fornecedor.getText();
            int ponto = Integer.parseInt(txt_ponto.getText());
            String categoria = txt_categoria.getText();
            double preco = Double.parseDouble(txt_preco.getText());

            //Comando SQL
            String comando = "update produto "
                    + " set nome_pro = '"+nome+"',"
                    + " descricao_pro = '"+descricao+"',"
                    + " quantidade_pro = "+quantidade+","
                    + " unidade_pro = '"+unidade+"',"
                    + " fornecedor_pro = '"+fornecedor+"',"
                    + " ponto_pro ="+ponto+","
                    + " categoria_pro ='"+categoria+"',"
                    + " preco_pro = "+preco+""
                    + " where codigo_pro ="+codigo;
            
           // Executar comando SQL
            banco.statement.executeUpdate(comando);
            JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso\n" , "Pronto", JOptionPane.OK_OPTION);
            
            // Mostra o primeiro registro novamente  
            banco.executeSQL(sql);
            banco.resultset.first();
            exibir_dados();
            
        }catch(SQLException e){
            // Se algo der errado, exibir mensagem
            JOptionPane.showMessageDialog(null, "Erro ao alterar informações!\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
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

        btn_voltar = new javax.swing.JButton();
        lbl_titulo = new javax.swing.JLabel();
        lbl_codigo_barras = new javax.swing.JLabel();
        lbl_codigo = new javax.swing.JLabel();
        lbl_nome = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        lbl_descricao = new javax.swing.JLabel();
        painel_descricao = new javax.swing.JScrollPane();
        txt_descricao = new javax.swing.JTextArea();
        lbl_quantidade = new javax.swing.JLabel();
        txt_quantidade = new javax.swing.JTextField();
        lbl_unidade = new javax.swing.JLabel();
        txt_unidade = new javax.swing.JTextField();
        lbl_fornecedor = new javax.swing.JLabel();
        txt_fornecedor = new javax.swing.JTextField();
        lbl_categoria = new javax.swing.JLabel();
        txt_categoria = new javax.swing.JTextField();
        lbl_preco = new javax.swing.JLabel();
        txt_preco = new javax.swing.JTextField();
        lbl_ponto = new javax.swing.JLabel();
        txt_ponto = new javax.swing.JTextField();
        btn_alterar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        lbl_alterar = new javax.swing.JLabel();
        lbl_excluir = new javax.swing.JLabel();
        lbl_salvar = new javax.swing.JLabel();
        lbl_voltar = new javax.swing.JLabel();
        btn_estoque = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Produtos");
        setResizable(false);

        btn_voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair.png"))); // NOI18N
        btn_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voltarActionPerformed(evt);
            }
        });

        lbl_titulo.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_titulo.setText("Cadastro de Produtos");

        lbl_codigo_barras.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_codigo_barras.setText("Código de Barras");

        lbl_codigo.setText("Código do Produto");

        lbl_nome.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_nome.setText("Nome");

        txt_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nomeActionPerformed(evt);
            }
        });

        lbl_descricao.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_descricao.setText("Descrição");

        txt_descricao.setColumns(20);
        txt_descricao.setRows(5);
        painel_descricao.setViewportView(txt_descricao);

        lbl_quantidade.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_quantidade.setText("Quantidade");

        lbl_unidade.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_unidade.setText("Unidade de Medida");

        lbl_fornecedor.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_fornecedor.setText("Fornecedor");

        lbl_categoria.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_categoria.setText("Categoria");

        txt_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_categoriaActionPerformed(evt);
            }
        });

        lbl_preco.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_preco.setText("Preço Unitário R$");

        lbl_ponto.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_ponto.setText("Pontos de Desconto");

        btn_alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ALTERAR.png"))); // NOI18N
        btn_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterarActionPerformed(evt);
            }
        });

        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/EXCLUIR.png"))); // NOI18N
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        lbl_alterar.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_alterar.setText("Alterar");

        lbl_excluir.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_excluir.setText("Excluir");

        lbl_salvar.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_salvar.setText("Salvar");

        lbl_voltar.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_voltar.setText("Voltar");

        btn_estoque.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btn_estoque.setText("Controle de Estoque");
        btn_estoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_estoqueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(lbl_unidade)))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_alterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_excluir)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_fornecedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_ponto))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lbl_salvar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lbl_voltar)
                                .addGap(25, 25, 25)))))
                .addGap(14, 14, 14))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(painel_descricao)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_nome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_preco)
                        .addGap(93, 93, 93))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbl_quantidade)
                                .addComponent(lbl_categoria)
                                .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(lbl_titulo))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_codigo_barras)
                        .addGap(67, 67, 67)
                        .addComponent(lbl_codigo))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_descricao))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_preco, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_unidade, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_estoque)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_ponto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lbl_titulo)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_codigo_barras)
                    .addComponent(lbl_codigo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nome)
                    .addComponent(lbl_preco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_preco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbl_descricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painel_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_quantidade)
                    .addComponent(lbl_unidade)
                    .addComponent(lbl_fornecedor)
                    .addComponent(lbl_ponto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_unidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ponto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbl_categoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_estoque))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_alterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_alterar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_salvar)
                        .addComponent(lbl_voltar)
                        .addComponent(lbl_excluir)))
                .addGap(29, 29, 29))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voltarActionPerformed
        voltar_menu();
    }//GEN-LAST:event_btn_voltarActionPerformed

    private void txt_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomeActionPerformed

    private void txt_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_categoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_categoriaActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        salvar();
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        excluir();
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterarActionPerformed
        alterar();
    }//GEN-LAST:event_btn_alterarActionPerformed

    private void btn_estoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_estoqueActionPerformed
        // Teste do controle de estoque
        new frm_estoque().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_estoqueActionPerformed

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
            java.util.logging.Logger.getLogger(frm_produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_produto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_alterar;
    private javax.swing.JButton btn_estoque;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JButton btn_voltar;
    private javax.swing.JLabel lbl_alterar;
    private javax.swing.JLabel lbl_categoria;
    private javax.swing.JLabel lbl_codigo;
    private javax.swing.JLabel lbl_codigo_barras;
    private javax.swing.JLabel lbl_descricao;
    private javax.swing.JLabel lbl_excluir;
    private javax.swing.JLabel lbl_fornecedor;
    private javax.swing.JLabel lbl_nome;
    private javax.swing.JLabel lbl_ponto;
    private javax.swing.JLabel lbl_preco;
    private javax.swing.JLabel lbl_quantidade;
    private javax.swing.JLabel lbl_salvar;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JLabel lbl_unidade;
    private javax.swing.JLabel lbl_voltar;
    private javax.swing.JScrollPane painel_descricao;
    private javax.swing.JTextField txt_categoria;
    private javax.swing.JTextArea txt_descricao;
    private javax.swing.JTextField txt_fornecedor;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JTextField txt_ponto;
    private javax.swing.JTextField txt_preco;
    private javax.swing.JTextField txt_quantidade;
    private javax.swing.JTextField txt_unidade;
    // End of variables declaration//GEN-END:variables
}

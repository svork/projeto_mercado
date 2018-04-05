package gui;

// Importando bibliotecas
import java.sql.*; // SQL
import database.Banco; // Classe Banco de Dados
import javax.swing.JOptionPane; // Janelas de Mensagens
import model.trocaDeInf;
import net.proteanit.sql.DbUtils; // Mostrar banco de dados em tabelas

public class frm_estoque extends javax.swing.JFrame {
    
    
    // Estanciar objeto
        frm_produto frm = new frm_produto();
    // Variavel que ira armazenar o valor do local clicado
        String valorId, valorNome, valorPreco, valorDescricao, valorCategoria, valorQuantidade,
                valorUnidade, valorFornecedor, valorPontos;
    
    // Instancia da classe de Conexão
    Banco banco;

    // controlar erro de navegacao nos botoes proximo e anterior
    int navega = 0;

    // select principal
    String sql = "select " +
        "codigo_pro as 'Código', " +
        "nome_pro as 'Nome', " +
        "descricao_pro as 'Descrição', " +
        "quantidade_pro as 'Quantidade', " +
        "unidade_pro as 'Unidade', " +
        "fornecedor_pro as 'Fornecedor', " +
        "ponto_pro as 'Pontos de Desconto', " +
        "categoria_pro as 'Categoria', " +
        "preco_pro as 'Preço Unitário R$' " +
        "from produto";

    // Construtor
    public frm_estoque() {
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
            carregar_dados();
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar dados\n" + e, "Erro!", JOptionPane.ERROR_MESSAGE);
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
    
    // Método carregar_dados - este método preenche uma tabela com dados
    public void carregar_dados() {
        try {
            // Adicionando os dados do banco a tabela
            banco.resultset.beforeFirst();
            tbl_produto.setModel(DbUtils.resultSetToTableModel(banco.resultset)); 
        }
        catch (Exception e) {
            // Se algo der errado, exibir mensagem
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados\n" + e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Método para enviar o valor do local clicado
    public void envia_lc (){ 

         // Atribui a string o codigo do produto selecionado
        valorId = tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 0).toString();
        // Atribui a string o valorNome selecionado
        valorNome = tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 1).toString();
        // Atribui a string o valorDescricao selecionado
        valorDescricao = tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 2).toString();
        // Atribui a string o valorPreco selecionado
        valorQuantidade = tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 3).toString();

        // Atribui a string o valorUnidade selecionado
        valorUnidade = tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 4).toString(); 
        // Atribui a string o valorFornecedor selecionado
        valorFornecedor = tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 5).toString();
        // Atribui a string o valorPontos selecionado
        valorPontos = tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 6).toString();
        // Atribui a string o valorCategoria selecionado
        valorCategoria = tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 7).toString();
        // Atribui a string o valorPreco selecionado
        valorPreco = tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 8).toString();

        // Cria o frm_produto
            if(frm==null){
                frm = new frm_produto();
                frm.setLocationRelativeTo(null);
                frm.setVisible(true);
                frm.setResizable(false);
            }
            else{
                frm.setLocationRelativeTo(null);
                frm.setVisible(true);
                frm.setResizable(false);
            }
        // Envia o valor do codigo do produto para o metodo da classe produto
            frm.enviar_dados(valorId, valorNome, valorPreco, valorDescricao, valorCategoria, valorQuantidade, valorUnidade, valorFornecedor, valorPontos);
        
    }
    
    // Cria um novo Produto no banco de dados
    public void add(){
        try{
        
        String sql = "insert into produto (nome_pro, descricao_pro, quantidade_pro, unidade_pro, fornecedor_pro, ponto_pro, categoria_pro, preco_pro) values ('Digite o Nome do produto', 'Descricao do produto', 0, 'Quilos', 'Fornecedor', 0, 'Não Perecível', 0.99);";
        
        // Executar comando SQL
            banco.statement.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Novo produto adicionado.", "Pronto", JOptionPane.YES_OPTION);

 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Adicione os valores!\n" + e, "Erro!", JOptionPane.DEFAULT_OPTION);  
        }
        
        //Refresh na página
        frm_estoque novo = new frm_estoque();
        novo.setVisible(true);
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_voltar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        painel_produto = new javax.swing.JScrollPane();
        tbl_produto = new javax.swing.JTable();
        btn_voltar = new javax.swing.JButton();
        lbl_atualizar = new javax.swing.JLabel();
        btn_atualizar = new javax.swing.JButton();
        btn_atualizar1 = new javax.swing.JButton();
        lbl_atualizar1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Estoque");
        setResizable(false);

        lbl_voltar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_voltar.setText("Voltar");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Controle de Estoque"));

        tbl_produto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        painel_produto.setViewportView(tbl_produto);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painel_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 1171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painel_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        btn_voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Voltar.png"))); // NOI18N
        btn_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voltarActionPerformed(evt);
            }
        });

        lbl_atualizar.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_atualizar.setText("Atualizar");

        btn_atualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Refresh_Icon.png"))); // NOI18N
        btn_atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atualizarActionPerformed(evt);
            }
        });

        btn_atualizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar.png"))); // NOI18N
        btn_atualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atualizar1ActionPerformed(evt);
            }
        });

        lbl_atualizar1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_atualizar1.setText("Adicionar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_atualizar))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_atualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_atualizar1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbl_voltar)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_voltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_atualizar)
                            .addComponent(lbl_voltar)))
                    .addComponent(btn_atualizar)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_atualizar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_atualizar1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voltarActionPerformed
        voltar_menu();
    }//GEN-LAST:event_btn_voltarActionPerformed

    private void btn_atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atualizarActionPerformed
        
        envia_lc();
        this.dispose();
        
    }//GEN-LAST:event_btn_atualizarActionPerformed

    private void btn_atualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atualizar1ActionPerformed
        add();
    }//GEN-LAST:event_btn_atualizar1ActionPerformed

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
            java.util.logging.Logger.getLogger(frm_estoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_estoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_estoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_estoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_estoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_atualizar;
    private javax.swing.JButton btn_atualizar1;
    private javax.swing.JButton btn_voltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_atualizar;
    private javax.swing.JLabel lbl_atualizar1;
    private javax.swing.JLabel lbl_voltar;
    private javax.swing.JScrollPane painel_produto;
    private javax.swing.JTable tbl_produto;
    // End of variables declaration//GEN-END:variables
}

package gui;

// Importando bibliotecas
import java.sql.*; // SQL
import database.Banco; // Classe Banco de Dados
import javax.swing.JOptionPane; // Janelas de Mensagens
import net.proteanit.sql.DbUtils; // Mostrar banco de dados em tabelas

public class frm_estoque extends javax.swing.JFrame {
    
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
        new frm_produto().setVisible(true);
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_voltar = new javax.swing.JButton();
        lbl_voltar = new javax.swing.JLabel();
        lbl_titulo = new javax.swing.JLabel();
        painel_produto = new javax.swing.JScrollPane();
        tbl_produto = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Estoque");
        setResizable(false);

        btn_voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair.png"))); // NOI18N
        btn_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voltarActionPerformed(evt);
            }
        });

        lbl_voltar.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_voltar.setText("Voltar");

        lbl_titulo.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_titulo.setText("Controle de Estoque");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_voltar)
                .addGap(49, 49, 49))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(96, 547, Short.MAX_VALUE)
                .addComponent(btn_voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(253, 253, 253)
                        .addComponent(lbl_titulo))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(painel_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbl_titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(painel_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_voltar)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JButton btn_voltar;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JLabel lbl_voltar;
    private javax.swing.JScrollPane painel_produto;
    private javax.swing.JTable tbl_produto;
    // End of variables declaration//GEN-END:variables
}

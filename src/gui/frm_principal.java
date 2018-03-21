package gui;

// Bibliotecas
import javax.swing.JOptionPane; // Janelas de Mensagens

public class frm_principal extends javax.swing.JFrame {

    // Construtor
    public frm_principal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_empresa = new javax.swing.JLabel();
        btn_sair = new javax.swing.JButton();
        btn_funcionario = new javax.swing.JButton();
        lbl_titulo = new javax.swing.JLabel();
        BackGround = new javax.swing.JLabel();
        BackGround1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menu_sair = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menu_empresa = new javax.swing.JMenuItem();
        menu_grupo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Green Market System");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_empresa.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lbl_empresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Supermercado.png"))); // NOI18N
        getContentPane().add(lbl_empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 680, -1));

        btn_sair.setBackground(new java.awt.Color(255, 255, 255));
        btn_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair.png"))); // NOI18N
        btn_sair.setText("Sair");
        btn_sair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_sair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairActionPerformed(evt);
            }
        });
        getContentPane().add(btn_sair, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 290, 80, 100));

        btn_funcionario.setBackground(new java.awt.Color(255, 255, 255));
        btn_funcionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novoCliente.png"))); // NOI18N
        btn_funcionario.setText("Cadastro de Funcionário");
        btn_funcionario.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btn_funcionario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_funcionario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_funcionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_funcionarioActionPerformed(evt);
            }
        });
        getContentPane().add(btn_funcionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        lbl_titulo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lbl_titulo.setText("Green Market System v0.1");
        getContentPane().add(lbl_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        BackGround.setBackground(new java.awt.Color(255, 255, 255));
        BackGround.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/BackGround.png"))); // NOI18N
        BackGround.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(243, 255, 255), 1, true));
        getContentPane().add(BackGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 680, 340));

        BackGround1.setBackground(new java.awt.Color(255, 255, 255));
        BackGround1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BackGround1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/BackGround.png"))); // NOI18N
        BackGround1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(243, 255, 255), 1, true));
        getContentPane().add(BackGround1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 680, 90));

        jMenu1.setText("Arquivo");

        menu_sair.setText("Sair");
        menu_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_sairActionPerformed(evt);
            }
        });
        jMenu1.add(menu_sair);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sobre");

        menu_empresa.setText("Empresa");
        jMenu2.add(menu_empresa);

        menu_grupo.setText("Grupo");
        jMenu2.add(menu_grupo);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairActionPerformed
         // Botão pergunta se o usuário quer realmente sair do programa
        if(JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Sair do Programa", JOptionPane.YES_NO_OPTION) == 0 ){
            this.dispose();
        }
    }//GEN-LAST:event_btn_sairActionPerformed

    private void btn_funcionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_funcionarioActionPerformed
        // Esse botão chama o form de cadastro de Funcionários
        new frm_funcionario().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_funcionarioActionPerformed

    private void menu_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_sairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_menu_sairActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(frm_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackGround;
    private javax.swing.JLabel BackGround1;
    private javax.swing.JButton btn_funcionario;
    private javax.swing.JButton btn_sair;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lbl_empresa;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JMenuItem menu_empresa;
    private javax.swing.JMenuItem menu_grupo;
    private javax.swing.JMenuItem menu_sair;
    // End of variables declaration//GEN-END:variables
}

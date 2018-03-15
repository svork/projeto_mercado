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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_empresa.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lbl_empresa.setText("Supermercado Pague Mais ou Menos");

        btn_sair.setText("SAIR");
        btn_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairActionPerformed(evt);
            }
        });

        btn_funcionario.setText("Cadastro de Funcionários");
        btn_funcionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_funcionarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_sair)
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(lbl_empresa))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btn_funcionario)))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lbl_empresa)
                .addGap(47, 47, 47)
                .addComponent(btn_funcionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 317, Short.MAX_VALUE)
                .addComponent(btn_sair)
                .addGap(25, 25, 25))
        );

        pack();
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
    private javax.swing.JButton btn_funcionario;
    private javax.swing.JButton btn_sair;
    private javax.swing.JLabel lbl_empresa;
    // End of variables declaration//GEN-END:variables
}

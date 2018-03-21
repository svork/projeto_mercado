package gui;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class frm_splash extends javax.swing.JFrame {
    
    

    public frm_splash() {
        initComponents();
        // J Barra de progresso
        new Thread() {
            public void run() {
                for(int i=0; i<101 ; i++){
                try{
                    sleep(100);
                jPB.setValue(i);
                if (jPB.getValue() >= 30){
                    lbl.setText("Carregando Sistema...");
                }
                if (jPB.getValue() >= 60){
                    lbl.setText("Carregando Banco de Dados...");
                }
                if (jPB.getValue() >= 90){
                    lbl.setText("Carregando Interface...");sleep(40);
                }
                    
                }catch(InterruptedException ex){
                    
                }
            }
                    
                    // Criar e chamar o próximo frm
                    frm_principal fp = new frm_principal();
                    //fechar janela atual
                    dispose();
                    //Abrir nova janela (frm)
                    fp.setVisible(true);
                
            }
        }.start();
          
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl = new javax.swing.JLabel();
        jPB = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl.setFont(new java.awt.Font("Lucida Sans", 2, 14)); // NOI18N
        lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl.setText("Carregando");
        getContentPane().add(lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 260, 20));

        jPB.setForeground(new java.awt.Color(51, 255, 51));
        getContentPane().add(jPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 258, 34));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logotipo.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_splash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jPB;
    private javax.swing.JLabel lbl;
    // End of variables declaration//GEN-END:variables
}

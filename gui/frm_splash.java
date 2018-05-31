package gui;

// Importando Bibliotecas
import static java.lang.Thread.sleep; // Contador de Tempo da Progress Bar

public class frm_splash extends javax.swing.JFrame {
    
    // Construtor
    public frm_splash() {
        initComponents();
       
        // Barra de progresso
        new Thread() {
            public void run() {
                for(int i=0; i<101 ; i++){
                    try{
                        sleep(100);
                        jPB.setValue(i);
                        
                        // A cada incremento da progress bar, exibir um texto diferente    
                        if (jPB.getValue() >= 2){
                            lbl_texto.setText("Carregando Sistema");
                        }
                        
                        if (jPB.getValue() >= 10){
                            lbl_texto.setText("Carregando Sistema.");
                        }
                        if (jPB.getValue() >= 12){
                            lbl_texto.setText("Carregando Sistema..");
                        }
                        if (jPB.getValue() >= 14){
                            lbl_texto.setText("Carregando Sistema...");
                        }

                        if (jPB.getValue() >= 60){
                            lbl_texto.setText("Carregando Banco de Dados...");
                        }
                        
                        if (jPB.getValue() >= 90){
                            lbl_texto.setText("Carregando Interface...");
                            sleep(40);
                        }
                    }
                    
                    catch(InterruptedException ex){
                        // Se der erro, não faz nada??
                    }
                }
                    
                // Chamar o próximo form Principal
                new frm_principal().setVisible(true);
                    
                // Fechar janela atual
                dispose();                     
            }
        }.start();  // Não entendi este método @svork on Saturday, March 24th 2018        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_texto = new javax.swing.JLabel();
        jPB = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_texto.setFont(new java.awt.Font("Lucida Sans", 2, 14)); // NOI18N
        lbl_texto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_texto.setText("Carregando");
        getContentPane().add(lbl_texto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 260, 20));

        jPB.setForeground(new java.awt.Color(51, 255, 51));
        getContentPane().add(jPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 258, 34));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logotipo.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 360));

        pack();
        setLocationRelativeTo(null);
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
    private javax.swing.JLabel lbl_texto;
    // End of variables declaration//GEN-END:variables
}

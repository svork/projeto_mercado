package gui;

 // Importando bibliotecas
import java.sql.*;
import database.Banco;
import javax.swing.JOptionPane;

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
        try{
            // Procura o primeiro registro no banco
            banco.resultset.first();
            exibir_dados();
        }
        catch (SQLException e){            
            JOptionPane.showMessageDialog(null,"Erro ao acessar dados\n" + e,"Erro!",JOptionPane.ERROR_MESSAGE);            
        }
    }
    
    // Método exibir_dados - mostra os dados na tela
    public void exibir_dados() {
        try{
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
        }
        catch (SQLException e){            
            if (navega == 1){ 
                JOptionPane.showMessageDialog(null,"Erro!\nVocê já está no primeiro registro.\n" + e,"Erro!",JOptionPane.ERROR_MESSAGE);                
            }
            else if (navega == 2){                
                JOptionPane.showMessageDialog(null,"Erro!\nVocê já está no último registro.\n" + e,"Erro!",JOptionPane.ERROR_MESSAGE);                
            }
            else {                
                JOptionPane.showMessageDialog(null,"Erro ao acessar dados\n" + e,"Erro!",JOptionPane.ERROR_MESSAGE);
                navega = 0;
            }
        }
    }
    
    // Método salvar - cria um novo registro
    public void salvar(){
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
            + "("+tipo+", '"+nome+"', '"+cpf+"', '"+endereco+"', '"+data_nascto+"', '"+funcao+"', '"+telefone+"', '"+estado_civil+"', "+salario+")";
            
            // Executar comando SQL
            banco.statement.executeUpdate(comando);
            JOptionPane.showMessageDialog(null, "Informações salvas com sucesso.","Pronto",JOptionPane.OK_OPTION);
            
            // Mostra o primeiro registro novamente  
            banco.executeSQL(sql);
            banco.resultset.first();            
            exibir_dados();
            
        }
        catch (SQLException e){            
            JOptionPane.showMessageDialog(null, "Erro ao salvar informações!\n" + e,"Erro!",JOptionPane.ERROR_MESSAGE);            
        } 
    }
    
    // Método excluir - exclui um registro
    public void excluir(){
        try {
            // Busca no banco o registro atual
            banco.executeSQL("select * from funcionario where id_fun = " + lbl_id.getText());
            banco.resultset.first();
            
            // Mensagem ao usuário para confirma a exclusão
            String mensagem = "Tem certeza que deseja excluir o funcionário?\n" + banco.resultset.getString(3) + "\nCPF: " + banco.resultset.getString(4);
            
            // Verifica se o usuário clicou no SIM e deleta o funcionário, se não, faz NADA
            if (JOptionPane.showConfirmDialog(null, mensagem, "Excluir funcionário?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                
                // Exclui o funcionário e se der certo, exibe uma mensagem ao usuário
                if (banco.statement.executeUpdate("delete from funcionario where id_fun = " + lbl_id.getText()) == 1){
                    JOptionPane.showMessageDialog(null, "O funcionário foi excluído com sucesso.");
                    
                    // Mostra o primeiro registro novamente                        
                    banco.executeSQL(sql);
                    banco.resultset.first();
                    exibir_dados();
                }                
            }    
        }
        catch (SQLException e){            
            JOptionPane.showMessageDialog(null, "Erro ao excluir registro!\n" + e,"Erro!",JOptionPane.ERROR_MESSAGE);            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_salario = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        txt_cpf = new javax.swing.JTextField();
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
        lbl_id = new javax.swing.JLabel();
        lbl_tipo = new javax.swing.JLabel();
        txt_tipo = new javax.swing.JTextField();
        lbl_estado_civil = new javax.swing.JLabel();
        btn_excluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_salario.setText("Salário");

        txt_cpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cpfActionPerformed(evt);
            }
        });

        lbl_codigo.setText("Código");

        btn_salvar.setText("Salvar");
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        lbl_titulo.setText("Cadastro de Funcionários");

        lbl_nome.setText("Nome");

        lbl_cpf.setText("CPF");

        lbl_endereco.setText("Endereço");

        lbl_data_nascto.setText("Data Nascimento");

        lbl_funcao.setText("Função");

        lbl_telefone.setText("Telefone");

        lbl_id.setText("Código");

        lbl_tipo.setText("Tipo");

        txt_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tipoActionPerformed(evt);
            }
        });

        lbl_estado_civil.setText("Estado Civil");

        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_nome)
                                    .addComponent(lbl_cpf)
                                    .addComponent(lbl_endereco)
                                    .addComponent(lbl_codigo))
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_nome)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(lbl_tipo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_tipo, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                                    .addComponent(txt_endereco)
                                    .addComponent(lbl_id)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_data_nascto)
                                    .addComponent(lbl_funcao)
                                    .addComponent(lbl_telefone)
                                    .addComponent(lbl_estado_civil))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_telefone, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                    .addComponent(txt_funcao)
                                    .addComponent(txt_data_nascto)
                                    .addComponent(txt_estado_civil))
                                .addGap(70, 70, 70)
                                .addComponent(lbl_salario)
                                .addGap(18, 18, 18)
                                .addComponent(txt_salario, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(lbl_titulo)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_titulo)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_codigo)
                    .addComponent(lbl_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_nome)
                            .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_cpf)
                            .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_tipo)
                            .addComponent(txt_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_endereco)
                            .addComponent(txt_endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lbl_data_nascto))
                    .addComponent(txt_data_nascto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_funcao)
                    .addComponent(txt_funcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_telefone)
                    .addComponent(txt_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_estado_civil)
                    .addComponent(lbl_salario)
                    .addComponent(txt_estado_civil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_salario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_cpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cpfActionPerformed

    }//GEN-LAST:event_txt_cpfActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        salvar();
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void txt_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tipoActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        excluir();
    }//GEN-LAST:event_btn_excluirActionPerformed

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
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JLabel lbl_codigo;
    private javax.swing.JLabel lbl_cpf;
    private javax.swing.JLabel lbl_data_nascto;
    private javax.swing.JLabel lbl_endereco;
    private javax.swing.JLabel lbl_estado_civil;
    private javax.swing.JLabel lbl_funcao;
    private javax.swing.JLabel lbl_id;
    private javax.swing.JLabel lbl_nome;
    private javax.swing.JLabel lbl_salario;
    private javax.swing.JLabel lbl_telefone;
    private javax.swing.JLabel lbl_tipo;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JTextField txt_cpf;
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

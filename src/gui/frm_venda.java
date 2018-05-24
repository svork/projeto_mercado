package gui;

// Importando bibliotecas
import database.Banco;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class frm_venda extends javax.swing.JFrame {

    // Instancia da classe de Conexão
    Banco banco;
    
    // Variável inteira para guardar o número do item na venda
    int numero_item = 0;

    // Construtor
    public frm_venda() {
        initComponents();
        
        // Criando objeto da classe Banco
        banco = new Banco();

        // Abrir conexão com o banco de dados
        banco.connect();  
        
        // Campos que não devem ser alterados, somente leitura
        txt_descricao_produto.setEditable(false);
        txt_valor_unitario_produto.setEditable(false);
        txt_ponto_produto.setEditable(false);
        
    }

    // Método voltar_menu - volta ao menu principal
    public void voltar_menu(){
        // Esse botão volta ao form Principal
        new frm_principal().setVisible(true);
        this.dispose();
        
        // Fechar a conexão com o banco
        banco.disconnect();
    }
    
    // Método buscar_produto - recebe o código de um produto e busca no banco 
    public void buscar_produto(int codigo){
        try{
            //Comando SQL
            String comando = "select descricao_pro, preco_pro, ponto_pro from produto where codigo_pro = " + codigo;
            
            // Executar comando SQL
            banco.executeSQL(comando);
                       
            // Mostra o primeiro registro novamente  
            banco.resultset.first();
            
            // Mostrar informações nos campos Descricao e Valor do Produto
            txt_descricao_produto.setText(banco.resultset.getString(1));
            txt_valor_unitario_produto.setText(banco.resultset.getString(2));
            txt_ponto_produto.setText(banco.resultset.getString(3));
            
        }catch(SQLException e){
            // Se algo der errado, limpar o código do produto e mostrar mensagem de erro
            txt_codigo_produto.setText("");
            JOptionPane.showMessageDialog(null, "Erro! Produto não encontrado\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    // Método buscar_cliente - Mostra a quantidade de pontos do cliente
    public void buscar_cliente(int codigo){
        try{
            //Comando SQL
            String comando = "select nome_cli, ponto_cli from cliente where codigo_cli =" + codigo;
            
            // Executar comando SQL
            banco.executeSQL(comando);
                       
            // Mostra o primeiro registro novamente  
            banco.resultset.first();
            
            // Mostrar informações nos campos Nome e Pontos do Cliente
            lbl_nome_cliente.setText(banco.resultset.getString(1));
            lbl_pontos_cliente.setText("Pontos: " + banco.resultset.getString(2));
            
            // Regra de Negócio, cada ponto vale R$ 1,00 de desconto
            lbl_desconto.setText(banco.resultset.getString(2));
            
        }catch(SQLException e){
            // Se algo der errado, limpar o código do cliente e mostrar mensagem de erro
            txt_codigo_cliente.setText("");
            JOptionPane.showMessageDialog(null, "Erro! Cliente não encontrado\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Método usar_pontos_desconto - Usa os pontos do cliente para dar desconto no valor total
    public void usar_pontos_desconto(){
        try {
            // Armazena o codigo pesquisado em uma variável
            String codigo = txt_codigo_cliente.getText();
        
            // Declaração de variáveis e calculo de desconto
            int totalinicial, desconto, totalfinal = 0;
        
            // Receber o valor totalinicial e o desconto
            totalinicial =  Integer.parseInt(lbl_valor_venda.getText());
            desconto = Integer.parseInt(lbl_desconto.getText());
            
            // Cálculo do Desconto, Total menos o desconto
            totalfinal = totalinicial - desconto;
            System.out.println(totalfinal);
        
            // Passa o valor com desconto no lbl
            lbl_valor_venda.setText(Integer.toString(totalfinal));
        
            // Update no banco referente a atualização depois de usar os pontos
            String comando = "update cliente, set ponto_cli = 0 , where ponto_cli = " + codigo;
        
            // Executar comando SQL
            banco.executeSQL(comando);
        
        } catch(Exception e){
            // Se algo der errado, mostrar mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro! Comando inválido\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    // Método incluir_produto_tabela - Insere o produto que foi buscado na tabela de produtos
    public void incluir_produto_tabela(){
        try {
            // Vetor de Strings para guardar os valores
            String [] produto = new String[6];
        
            // Guardar os valores na tabela
            produto[0] = Integer.toString(numero_item); // Número do produto na lista
            produto[1] = txt_quantidade_produto.getText(); // Quantidade do produto que foi comprada
            produto[2] = txt_ponto_produto.getText(); // Pontos de Desconto do produto
            produto[3] = txt_descricao_produto.getText(); // Descrição do produto
            produto[4] = txt_valor_unitario_produto.getText(); // Valor unitário do produto
            
            // Sub-total é calculado multiplicando a quantidade do produto pelo seu valor unitario
            produto[5] = Double.toString(Double.parseDouble(produto[1]) * Double.parseDouble(produto[4]));
        
            // Incrementar o número do item
            numero_item ++;
            
            // Adicionar o vetor com as informações a tabela
            tabela_lista_produto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {produto[0], produto[1], produto[2], produto[3], produto[4], produto[5],},
            },
            new String [] {
                "ITEM", "QTDE", "PONTOS", "PRODUTO", "VLR UNIT R$", "SUB-TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };
        });
          
        } catch(Exception e){
            // Se algo der errado, mostrar mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro! Comando inválido\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    // Método calcular_valor_total - Mostra o valor total da venda a cada produto que foi inserido
    public void calcular_valor_total(){
        
    }
            
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_total_palavra = new javax.swing.JLabel();
        txt_observacoes_venda = new javax.swing.JTextField();
        txt_descricao_produto = new javax.swing.JTextField();
        lbl_titulo = new javax.swing.JLabel();
        lbl_valor_venda = new javax.swing.JLabel();
        lbl_codigo_produto = new javax.swing.JLabel();
        lbl_valor_unitario_produto = new javax.swing.JLabel();
        lbl_quantidade_produto = new javax.swing.JLabel();
        lbl_descricao_produto = new javax.swing.JLabel();
        lbl_numero_venda = new javax.swing.JLabel();
        lbl_data_venda = new javax.swing.JLabel();
        lbl_hora_venda = new javax.swing.JLabel();
        lbl_funcionario = new javax.swing.JLabel();
        btn_voltar = new javax.swing.JButton();
        lbl_pontos_cliente = new javax.swing.JLabel();
        lbl_nome_cliente = new javax.swing.JLabel();
        btn_pesquisar_codigo_produto = new javax.swing.JButton();
        txt_valor_unitario_produto = new javax.swing.JTextField();
        txt_quantidade_produto = new javax.swing.JTextField();
        txt_codigo_produto = new javax.swing.JTextField();
        btn_confirmar = new javax.swing.JButton();
        lbl_rodape_venda = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_pontos_cliente_cabecalho = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btn_incluir_produto = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        btn_pontos_descontos = new javax.swing.JButton();
        btn_buscar_codigo_cliente = new javax.swing.JButton();
        txt_codigo_cliente = new javax.swing.JTextField();
        lbl_valor_desconto_cliente = new javax.swing.JLabel();
        painel_tabela = new javax.swing.JScrollPane();
        tabela_lista_produto = new javax.swing.JTable();
        lbl_codigo_cliente = new javax.swing.JLabel();
        lbl_desconto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_ponto_produto = new javax.swing.JTextField();
        lbl_ponto_produto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_total_palavra.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        lbl_total_palavra.setForeground(new java.awt.Color(255, 255, 255));
        lbl_total_palavra.setText("Total: R$");
        getContentPane().add(lbl_total_palavra, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 600, 130, 40));

        txt_observacoes_venda.setForeground(new java.awt.Color(204, 204, 204));
        txt_observacoes_venda.setText("Observações");
        txt_observacoes_venda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_observacoes_vendaActionPerformed(evt);
            }
        });
        getContentPane().add(txt_observacoes_venda, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 610, 410, 20));

        txt_descricao_produto.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txt_descricao_produto.setFocusable(false);
        getContentPane().add(txt_descricao_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 620, -1));

        lbl_titulo.setFont(new java.awt.Font("BrowalliaUPC", 1, 24)); // NOI18N
        lbl_titulo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_titulo.setText("Green Market System - Vendas");
        getContentPane().add(lbl_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, -1));

        lbl_valor_venda.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        lbl_valor_venda.setForeground(new java.awt.Color(255, 255, 255));
        lbl_valor_venda.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_valor_venda.setText("00,00");
        getContentPane().add(lbl_valor_venda, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 600, 90, 40));

        lbl_codigo_produto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_codigo_produto.setForeground(new java.awt.Color(51, 102, 255));
        lbl_codigo_produto.setText("Código");
        getContentPane().add(lbl_codigo_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, -1, -1));

        lbl_valor_unitario_produto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_valor_unitario_produto.setForeground(new java.awt.Color(51, 102, 255));
        lbl_valor_unitario_produto.setText("Valor unitário R$");
        getContentPane().add(lbl_valor_unitario_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        lbl_quantidade_produto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_quantidade_produto.setForeground(new java.awt.Color(51, 102, 255));
        lbl_quantidade_produto.setText("Quantidade");
        getContentPane().add(lbl_quantidade_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, -1, -1));

        lbl_descricao_produto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_descricao_produto.setForeground(new java.awt.Color(51, 102, 255));
        lbl_descricao_produto.setText("Produto");
        getContentPane().add(lbl_descricao_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        lbl_numero_venda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_numero_venda.setForeground(new java.awt.Color(0, 51, 102));
        lbl_numero_venda.setText("Pedido N° 1626");
        getContentPane().add(lbl_numero_venda, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 80, -1, -1));

        lbl_data_venda.setBackground(new java.awt.Color(204, 204, 204));
        lbl_data_venda.setForeground(new java.awt.Color(153, 153, 153));
        lbl_data_venda.setText("20/04/2018");
        getContentPane().add(lbl_data_venda, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 110, -1, -1));

        lbl_hora_venda.setForeground(new java.awt.Color(153, 153, 153));
        lbl_hora_venda.setText("22:59");
        getContentPane().add(lbl_hora_venda, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 110, -1, -1));

        lbl_funcionario.setForeground(new java.awt.Color(153, 153, 153));
        lbl_funcionario.setText("Nome Usuário");
        getContentPane().add(lbl_funcionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, -1, -1));

        btn_voltar.setBackground(new java.awt.Color(0, 204, 0));
        btn_voltar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btn_voltar.setForeground(new java.awt.Color(255, 255, 255));
        btn_voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Template/botao3.png"))); // NOI18N
        btn_voltar.setText("Cancelar");
        btn_voltar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voltarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_voltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 540, 150, 50));

        lbl_pontos_cliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_pontos_cliente.setForeground(new java.awt.Color(0, 51, 102));
        lbl_pontos_cliente.setText("Pontos:");
        getContentPane().add(lbl_pontos_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 370, -1, -1));

        lbl_nome_cliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_nome_cliente.setForeground(new java.awt.Color(0, 51, 102));
        lbl_nome_cliente.setText("Nome do cliente");
        getContentPane().add(lbl_nome_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 335, -1, -1));

        btn_pesquisar_codigo_produto.setForeground(new java.awt.Color(255, 255, 255));
        btn_pesquisar_codigo_produto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Template/botao2.png"))); // NOI18N
        btn_pesquisar_codigo_produto.setText("Pesquisar");
        btn_pesquisar_codigo_produto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_pesquisar_codigo_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisar_codigo_produtoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_pesquisar_codigo_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, 100, 60));

        txt_valor_unitario_produto.setFocusable(false);
        getContentPane().add(txt_valor_unitario_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 120, -1));

        txt_quantidade_produto.setText("0");
        getContentPane().add(txt_quantidade_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 80, -1));
        getContentPane().add(txt_codigo_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 120, -1));

        btn_confirmar.setBackground(new java.awt.Color(0, 204, 0));
        btn_confirmar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btn_confirmar.setForeground(new java.awt.Color(255, 255, 255));
        btn_confirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Template/botao4.png"))); // NOI18N
        btn_confirmar.setText("Confirmar");
        btn_confirmar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(btn_confirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 600, 150, 40));

        lbl_rodape_venda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Template/bar4.png"))); // NOI18N
        getContentPane().add(lbl_rodape_venda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pagamento.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lbl_pontos_cliente_cabecalho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Template/bar5.png"))); // NOI18N
        getContentPane().add(lbl_pontos_cliente_cabecalho, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 320, 170, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Template/bar1.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        btn_incluir_produto.setForeground(new java.awt.Color(255, 255, 255));
        btn_incluir_produto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Template/botao1.png"))); // NOI18N
        btn_incluir_produto.setText("Incluir");
        btn_incluir_produto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_incluir_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_incluir_produtoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_incluir_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 100, 60));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Template/bar5.png"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 50, 170, 120));

        btn_pontos_descontos.setText("Usar Pontos");
        btn_pontos_descontos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pontos_descontosActionPerformed(evt);
            }
        });
        getContentPane().add(btn_pontos_descontos, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 440, -1, -1));

        btn_buscar_codigo_cliente.setText("Buscar Cliente");
        btn_buscar_codigo_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_codigo_clienteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_buscar_codigo_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 270, -1, -1));

        txt_codigo_cliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txt_codigo_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 240, 110, -1));

        lbl_valor_desconto_cliente.setText("Desconto R$: ");
        getContentPane().add(lbl_valor_desconto_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 410, -1, -1));

        tabela_lista_produto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "ITEM", "QTDE", "PONTOS", "PRODUTO", "VLR UNIT R$", "SUB-TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        painel_tabela.setViewportView(tabela_lista_produto);

        getContentPane().add(painel_tabela, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 770, 440));

        lbl_codigo_cliente.setText("Código do Cliente");
        getContentPane().add(lbl_codigo_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 220, -1, -1));

        lbl_desconto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_desconto.setText("00");
        lbl_desconto.setToolTipText("");
        lbl_desconto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(lbl_desconto, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 410, 30, -1));

        jLabel2.setText(",00");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 410, 30, -1));

        txt_ponto_produto.setFocusable(false);
        getContentPane().add(txt_ponto_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 120, -1));

        lbl_ponto_produto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_ponto_produto.setForeground(new java.awt.Color(51, 102, 255));
        lbl_ponto_produto.setText("Pontos");
        getContentPane().add(lbl_ponto_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_observacoes_vendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_observacoes_vendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_observacoes_vendaActionPerformed

    private void btn_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voltarActionPerformed
        new frm_principal().setVisible(true);
        this.dispose();
        voltar_menu();
    }//GEN-LAST:event_btn_voltarActionPerformed

    private void btn_pesquisar_codigo_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisar_codigo_produtoActionPerformed
        // Este método aceita apenas numeros inteiros como código do produtp
        try {
            int codigo = Integer.parseInt(txt_codigo_produto.getText());
            buscar_produto(codigo);
        }
        catch (Exception e){
            // Se algo der errado, limpar o código do produto e mostrar mensagem de erro
            txt_codigo_produto.setText("");
            JOptionPane.showMessageDialog(null, "Erro! Código Inválido!\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btn_pesquisar_codigo_produtoActionPerformed

    private void btn_buscar_codigo_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_codigo_clienteActionPerformed
        // Este método aceita apenas numeros inteiros como código do produtp
        try {
            int codigo = Integer.parseInt(txt_codigo_cliente.getText());
            buscar_cliente(codigo);
        }
        catch (Exception e){
            // Se algo der errado, limpar o código do produto e mostrar mensagem de erro
            txt_codigo_cliente.setText("");
            JOptionPane.showMessageDialog(null, "Erro! Código Inválido!\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_buscar_codigo_clienteActionPerformed

    private void btn_pontos_descontosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pontos_descontosActionPerformed
        // TODO add your handling code here:
        usar_pontos_desconto();
    }//GEN-LAST:event_btn_pontos_descontosActionPerformed

    private void btn_incluir_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_incluir_produtoActionPerformed
        // TODO add your handling code here:
        incluir_produto_tabela();
    }//GEN-LAST:event_btn_incluir_produtoActionPerformed

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
            java.util.logging.Logger.getLogger(frm_venda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_venda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_venda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_venda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_venda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar_codigo_cliente;
    private javax.swing.JButton btn_confirmar;
    private javax.swing.JButton btn_incluir_produto;
    private javax.swing.JButton btn_pesquisar_codigo_produto;
    private javax.swing.JButton btn_pontos_descontos;
    private javax.swing.JButton btn_voltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lbl_codigo_cliente;
    private javax.swing.JLabel lbl_codigo_produto;
    private javax.swing.JLabel lbl_data_venda;
    private javax.swing.JLabel lbl_desconto;
    private javax.swing.JLabel lbl_descricao_produto;
    private javax.swing.JLabel lbl_funcionario;
    private javax.swing.JLabel lbl_hora_venda;
    private javax.swing.JLabel lbl_nome_cliente;
    private javax.swing.JLabel lbl_numero_venda;
    private javax.swing.JLabel lbl_ponto_produto;
    private javax.swing.JLabel lbl_pontos_cliente;
    private javax.swing.JLabel lbl_pontos_cliente_cabecalho;
    private javax.swing.JLabel lbl_quantidade_produto;
    private javax.swing.JLabel lbl_rodape_venda;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JLabel lbl_total_palavra;
    private javax.swing.JLabel lbl_valor_desconto_cliente;
    private javax.swing.JLabel lbl_valor_unitario_produto;
    private javax.swing.JLabel lbl_valor_venda;
    private javax.swing.JScrollPane painel_tabela;
    private javax.swing.JTable tabela_lista_produto;
    private javax.swing.JTextField txt_codigo_cliente;
    private javax.swing.JTextField txt_codigo_produto;
    private javax.swing.JTextField txt_descricao_produto;
    private javax.swing.JTextField txt_observacoes_venda;
    private javax.swing.JTextField txt_ponto_produto;
    private javax.swing.JTextField txt_quantidade_produto;
    private javax.swing.JTextField txt_valor_unitario_produto;
    // End of variables declaration//GEN-END:variables
}

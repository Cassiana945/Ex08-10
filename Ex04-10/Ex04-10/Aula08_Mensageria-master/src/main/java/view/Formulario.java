package view;

import com.mensageria.aula08_mensageria.Aula08MensageriaApplication;
import com.mensageria.aula08_mensageria.KafkaBatchProducer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formulario {
    private JPanel jpPrincipal;
    private JPanel jpTitulo;
    private JPanel jpBtn;
    private JPanel jpConteudo;
    private JButton enviarBtn;
    private JRadioButton radioBtnAlta;
    private JRadioButton radioBtnMedia;
    private JRadioButton radioBtnBaixa;
    private JTextField textField;
    private JButton fecharBtn;
    private KafkaBatchProducer kafkaBatchProducer;



    public Formulario(KafkaBatchProducer kafkaBatchProducer) {
        this.kafkaBatchProducer = kafkaBatchProducer;
        eventosFormulario();
    }


    public void eventosFormulario() {

        enviarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textField.getText();
                if (message.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite uma mensagem");
                    return;
                }

                if (radioBtnAlta.isSelected()) {
                    kafkaBatchProducer.sendMessagesInBatchAlta(message);
                } else if (radioBtnMedia.isSelected()) {
                    kafkaBatchProducer.sendMessagesInBatchMedia(message);
                } else if (radioBtnBaixa.isSelected()) {
                    kafkaBatchProducer.sendMessagesInBatchBaixa(message);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma prioridade");
                }

                textField.setText("");
            }
        });

        fecharBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               System.exit(0);
            }
        });

        }



    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Aula08MensageriaApplication.class);
        KafkaBatchProducer kafkaBatchProducer = context.getBean(KafkaBatchProducer.class);


        JFrame frame = new JFrame("Formulario");
        frame.setContentPane(new Formulario(kafkaBatchProducer).jpPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    }


package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Vector;

public class CertificateReader extends JFrame
{
    private static final long serialVersionUID = 1L;

    final String   TERMIN        = "Срок действия сертификата%s";
    final String   VALID         = "действителен"               ;
    final String   INVALID       = "не действителен"            ;
    final String   CREATER       = "Издатель%s"                 ;
    final String   NUMBER        = "Серийный номер%s"           ;
    final String   START         = "Начало срока действия%s"    ;
    final String   END           = "Конец срока действия%s"     ;
    final String   OWNER         = "Владелец%s"                 ;
    final String   ALGORITM      = "Алгоритм подписи%s"         ;
    final String   SIGN          = "Подпись сертификата%s"      ;
    final String   LF            = "\n"                         ;
    final String   LF_SPACE      = " :\n      "                 ;

    KeyStore       keyStore      = null; // хранилище ключей и сертификатов
    JList<String>  lstAliases    = null;
    JTextField     txtFileName   = null;
    JTextArea      taCertificate = null;
    final  int     LIST_size     = 140 ;

    public CertificateReader()
    {
        setTitle("Просмотр хранилища сертификатов");
        setSize(600, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(createCtrl(), BorderLayout.SOUTH);
        getContentPane().add(createGUI (), BorderLayout.CENTER);

        setVisible(true);
    }
    private JPanel createCtrl()
    {
        JPanel pnlControls = new JPanel();

        pnlControls.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlControls.setSize(480, 40);

        txtFileName = new JTextField();
        txtFileName.setMinimumSize(new Dimension(6, 100));
        txtFileName.setPreferredSize(new Dimension(460, 25));
        txtFileName.setText("Выберите хранилище");

        JButton btnBrowseStore = new JButton();
        btnBrowseStore.setText("Хранилище");
        btnBrowseStore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadKeyStore();
            }
        });

        pnlControls.add(txtFileName   , null);
        pnlControls.add(btnBrowseStore, null);

        return pnlControls;
    }
    private JSplitPane createGUI()
    {
        lstAliases    = new JList<String>();
        taCertificate = new JTextArea(18, 30);
        taCertificate.setMargin(new Insets(5, 5, 5, 5));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent (new JScrollPane(lstAliases   ));
        splitPane.setRightComponent(new JScrollPane(taCertificate));
        splitPane.setDividerSize(8);
        splitPane.setDividerLocation(LIST_size);

        lstAliases.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                showCertificate(lstAliases.getSelectedValue());
                lstAliases.getSelectionModel().clearSelection();
            }
            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }
            @Override
            public void mousePressed(MouseEvent arg0) {
            }
            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
        });
        return splitPane;
    }

    void loadKeyStore()
    {
        FileInputStream fis;
        // Выбор хранилища сертификатов
        JFileChooser chooser = new JFileChooser();
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            txtFileName.setText(chooser.getSelectedFile().getAbsolutePath());
            try {
                // Чтение хранилище сертификатов
                keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                fis      = new FileInputStream(txtFileName.getText());
                keyStore.load(fis, null);
                Enumeration<String> E = keyStore.aliases();

                // Формирование набор сертификатов
                Vector<String> certs = new Vector<String>();
                while (E.hasMoreElements())
                    certs.add(  (String)E.nextElement() );
                // Размещение сертификатов в компоненте
                lstAliases.setListData(certs);
                invalidate();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(this ,
                        "Ошибка чтения хранилища сертификатов:\n" + e);
            }
        }
    }

    void showCertificate(final String name)
    {
        Certificate cert = null;
        try {
            // Чтение сертификата
            cert = keyStore.getCertificate(name);
            X509Certificate xcert = (X509Certificate) cert;

            String valid = "";
            try {
                xcert.checkValidity();
                valid = VALID;
            } catch (Exception ex){
                valid = INVALID;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String end   = sdf.format(xcert.getNotAfter ());
            String start = sdf.format(xcert.getNotBefore());
            String sign = new sun.misc.BASE64Encoder().encode( xcert.getSignature());

            String creater = xcert.getIssuerDN().getName();
            String owner   = xcert.getSubjectDN().getName();
            String number  = String.valueOf(xcert.getSerialNumber());
            String algo    = xcert.getSigAlgName();

            String info;
            info  = createLine(TERMIN  , valid  );
            info += createLine(CREATER , creater);
            info += createLine(NUMBER  , number );
            info += createLine(START   , start  );
            info += createLine(END     , end    );
            info += createLine(OWNER   , owner  );
            info += createLine(ALGORITM, algo   );
            info += createLine(SIGN    , sign   );
            taCertificate.setText(info);
        } catch (KeyStoreException ex1) {
            JOptionPane.showMessageDialog(this  ,
                    "Ошибка получения из хранилища сертификата под псевдонимом <<"+
                            lstAliases.getSelectedValue()+">>");
        } catch (NullPointerException ex1) {
            JOptionPane.showMessageDialog(this  ,
                    "Ошибка получения из хранилища сертификата под псевдонимом <<"+
                            lstAliases.getSelectedValue()+">>");
        }
    }

    protected String createLine (String templ, String text)
    {
        return String.format(templ, LF_SPACE + text + LF);
    }

    public static void main(String[] args) {
        new CertificateReader();
    }
}
